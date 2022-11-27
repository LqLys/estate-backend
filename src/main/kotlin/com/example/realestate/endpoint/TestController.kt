package com.example.realestate.endpoint

import com.example.realestate.domain.Scrapper
import com.example.realestate.domain.advertisement.Advertisement
import com.example.realestate.domain.advertisement.repository.AdvertisementRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/api/v1/test")
class TestController(private val scrapper: Scrapper, private val advertisementRepository: AdvertisementRepository) {


    @GetMapping
    fun test() {
        scrapper.scrap();
    }

//    @GetMapping(path = ["/create"])
//    fun create() {
//        val testEntity = Advertisement("ad", BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE);
//        advertisementRepository.save(testEntity);
//
//    }

    @GetMapping(path = ["/get"])
    fun get(): MutableList<Advertisement> {
        val a: String? = null;
        return advertisementRepository.findAll();
    }
}