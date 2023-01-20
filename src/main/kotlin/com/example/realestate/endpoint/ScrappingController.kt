package com.example.realestate.endpoint

import com.example.realestate.domain.ForwardAdvertisementScrapper
import com.example.realestate.domain.NewOtodomAdvertisementScrapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/scrap")
class ScrappingController(
    private val newAdvertisementsScrapper: NewOtodomAdvertisementScrapper,
    private val forwardAdvertisementScrapper: ForwardAdvertisementScrapper
) {


    @GetMapping("/forward")
    fun scrapForward(@RequestParam startPage: Int = 1){
        forwardAdvertisementScrapper.scrap(startPage);
    }

    @GetMapping("/new")
    fun scrapNew(){
        newAdvertisementsScrapper.scrap();
    }
}