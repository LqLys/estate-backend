package com.example.realestate.domain

import com.example.realestate.domain.advertisement.Advertisement
import com.example.realestate.domain.advertisement.repository.AdvertisementRepository
import com.example.realestate.domain.data.OtodomPageResponse
import com.example.realestate.domain.data.page_item.OtodomPageItemResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.jsoup.Jsoup
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Polygon
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import java.util.function.Consumer


@Service
class Scrapper(private val objectMapper: ObjectMapper, private val advertisementRepository: AdvertisementRepository,
private val districtFinder: DistrictFinder) {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
        const val INFINITE_LOOP_PREVENTION_MAX_PAGE: Int = 10000;
        const val OTODOM_PAGE_URL: String = "https://www.otodom.pl/pl/oferty/sprzedaz/mieszkanie/warszawa?";

    }

    fun getSlugs() {
        for (pageNr in 1 until INFINITE_LOOP_PREVENTION_MAX_PAGE) {
            val page: OtodomPageResponse? = getPage(pageNr)
            val pageSlugs = extractSlugsFromPage(page)
            if (pageSlugs.isEmpty()) {
                break
            }
            for (slug in pageSlugs) {
                val advertisementData: OtodomPageItemResponse? = getAdvertisementData(slug)

                advertisementData.let {
                    log.info("Getting data for: $slug")
                    val latitude = it?.props?.pageProps?.ad?.location?.coordinates?.latitude
                    val longitude = it?.props?.pageProps?.ad?.location?.coordinates?.longitude
                    val district = getDistrict(latitude, longitude);


                    val advertisement = Advertisement(
                        "https://www.otodom.pl/pl/oferta/$slug",
                        it?.props?.pageProps?.ad?.target?.price,
                        it?.props?.pageProps?.ad?.target?.pricePerMeter?.let { ppm -> BigDecimal(ppm) },
                        it?.props?.pageProps?.ad?.target?.area?.let { area -> BigDecimal(area) },
                        latitude,
                        longitude,
                        district,
                        "OTODOM",
                        it?.props?.pageProps?.ad?.title
                    )
                    advertisementRepository.save(advertisement);

                }
                println("a")
                sleep()
            }
//                        slugs.addAll(pageSlugs);
            sleep()
        }
    }

    fun scrap() {
        getSlugs();
    }
    private fun getDistrict(latitude: BigDecimal?, longitude: BigDecimal?): String?{
        return if(latitude != null && longitude != null){
            try {
                districtFinder.getDistrictForCoordinates(latitude.toDouble(), longitude.toDouble()) ?: "UNKNOWN"
            }catch (e: Exception){
                println(latitude)
                println(longitude)
                println(e)
                "ERROR"
            }
        } else{
            null
        }
    }

    private fun getPage(pageNr: Int): OtodomPageResponse? {
        try {
            log.info("Getting otodom page $pageNr")
            val url = OTODOM_PAGE_URL + "page=$pageNr&limit=72"
            val doc = Jsoup.connect(url).get()
            val dataJson = doc.body().getElementById("__NEXT_DATA__")
                ?.childNode(0)
                .toString()

            return objectMapper.readValue(dataJson)
        } catch (e: Exception) {
            log.error(String.format("Error getting otodom page %d", pageNr))
        }
        return null;
    }

    private fun sleep() {
        val randomTime = ThreadLocalRandom.current().nextInt(0, 20000 + 1)
        try {
            Thread.sleep((25000 + randomTime).toLong())
        } catch (e: InterruptedException) {
            log.error("Error sleeping")
            e.printStackTrace()
        }
    }

    private fun extractSlugsFromPage(page: OtodomPageResponse?): List<String> {
        return page?.props
            ?.pageProps
            ?.data
            ?.searchAds
            ?.items
            ?.mapNotNull { it.slug }
            .orEmpty()
    }

    private fun getAdvertisementData(slug: String): OtodomPageItemResponse? {
        try {
            val one = Jsoup.connect("https://www.otodom.pl/pl/oferta/$slug").get()
            val nextData = Objects.requireNonNull(one.body().getElementById("__NEXT_DATA__"))?.childNode(0)
            val nextDataJson = nextData.toString()
            return objectMapper.readValue(nextDataJson)
        } catch (e: Exception) {
            log.error("Error getting data for slug: $slug")
        }
        return null;
    }



}