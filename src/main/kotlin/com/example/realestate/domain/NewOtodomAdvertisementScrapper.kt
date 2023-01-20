package com.example.realestate.domain

import com.example.realestate.client.OtodomScrappingClient
import com.example.realestate.domain.advertisement.repository.AdvertisementRepository
import com.example.realestate.domain.data.OtodomPageResponse
import com.example.realestate.domain.data.page_item.OtodomPageItemResponse
import com.example.realestate.mapper.OtodomAdvertisementMapper
import com.example.realestate.worker.PageSlugsProcessintStatus
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.concurrent.ThreadLocalRandom


@Service
class NewOtodomAdvertisementScrapper(
    private val otodomScrappingClient: OtodomScrappingClient,
    private val advertisementRepository: AdvertisementRepository,
    private val mapper: OtodomAdvertisementMapper
) {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)

        @Volatile
        private var running: Boolean = false;
    }

    fun scrap() {
        while (!running) {
            try {
                var pageSlugs: List<String>;
                var pageNr = 1;
                var processingStatus: PageSlugsProcessintStatus;
                do {
                    val page: OtodomPageResponse? = otodomScrappingClient.getAdvertisementsPage(
                        pageNr, "&limit=72&market=ALL&by=LATEST&direction=DESC"
                    )
                    pageSlugs = page?.getSlugsFromPage().orEmpty()
                    processingStatus = processPageSlugs(pageSlugs)
                    log.info("Finished processing page: $pageNr")
                    sleep()
                    pageNr++
                } while (shouldContinueScrapping(pageSlugs, processingStatus))

            } catch (e: Exception) {
                log.error("Error scrapping new Otodom advertisements")
            } finally {
                running = false;
            }
            sleep();
        }
    }

    private fun shouldContinueScrapping(
        pageSlugs: List<String>, processingStatus: PageSlugsProcessintStatus
    ): Boolean {
        if (processingStatus == PageSlugsProcessintStatus.DUPLICATE_FOUND) {
            return false;
        }
        return pageSlugs.isNotEmpty()
    }

    private fun processPageSlugs(pageSlugs: List<String>): PageSlugsProcessintStatus {
        for (slug in pageSlugs) {
            val advertisementData: OtodomPageItemResponse? = otodomScrappingClient.getAdvertisementData(slug)
            if (advertisementData != null) {
                log.info("Getting data for: $slug")

                val advertisementUrl = "https://www.otodom.pl/pl/oferta/$slug"
                val advertisement = mapper.itemResponseToAdvertisement(advertisementData, advertisementUrl)
                val advertExists = advertisementRepository.findByUrl(advertisementUrl)

                if (advertExists != null) {
                    log.info("{$advertisementUrl} already exists, breaking loop")
                    return PageSlugsProcessintStatus.DUPLICATE_FOUND
                } else {
                    log.info("new advertisement found: $advertisementUrl")
                    advertisementRepository.upsertAdvertisementByUniqueUrl(advertisement)
                }
            }
            log.info("Finished processing: $slug")
            sleep()
        }
        return PageSlugsProcessintStatus.FINISHED;
    }


    private fun sleep() {
        val randomTime = ThreadLocalRandom.current().nextInt(0, 10000 + 1)
        try {
            Thread.sleep((10000 + randomTime).toLong())
        } catch (e: InterruptedException) {
            log.error("Error sleeping")
            e.printStackTrace()
        }
    }


}