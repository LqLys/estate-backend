package com.example.realestate.client

import com.example.realestate.domain.data.OtodomPageResponse
import com.example.realestate.domain.data.page_item.OtodomPageItemResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.jsoup.Jsoup
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

/**
 * Responsible for getting data from otodom
 */
@Service
class OtodomScrappingClient(private val objectMapper: ObjectMapper) {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
        const val OTODOM_PAGE_URL: String = "https://www.otodom.pl/pl/oferty/sprzedaz/mieszkanie/warszawa?";
    }


    fun getAdvertisementsPage(pageNr: Int, queryFilter: String): OtodomPageResponse? {
        try {
            log.info("Getting otodom page $pageNr")
            val url = OTODOM_PAGE_URL + "page=$pageNr" + queryFilter
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

    fun getAdvertisementData(slug: String): OtodomPageItemResponse? {
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