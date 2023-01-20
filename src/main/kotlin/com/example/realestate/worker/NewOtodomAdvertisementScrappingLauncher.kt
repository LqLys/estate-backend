package com.example.realestate.worker

import com.example.realestate.domain.ForwardAdvertisementScrapper
import com.example.realestate.domain.NewOtodomAdvertisementScrapper
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

//@Profile("!local")
@Component
class NewOtodomAdvertisementScrappingLauncher(
    private val scrapper: NewOtodomAdvertisementScrapper,
    private val forwardAdvertisementScrapper: ForwardAdvertisementScrapper
    ) {


//    @EventListener(ApplicationReadyEvent::class)
//    @Scheduled(fixedRate = 3, timeUnit = TimeUnit.SECONDS)
    fun scrapNewAdvertisements() {
        scrapper.scrap()
    }

//    @Scheduled(fixedRate = 3, timeUnit = TimeUnit.SECONDS)
    fun scrapAdvertisementsFromOldest(){
        forwardAdvertisementScrapper.scrap(1);
    }



}