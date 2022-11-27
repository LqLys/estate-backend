package com.example.realestate.endpoint

import com.example.realestate.domain.DistrictFinder
import com.example.realestate.domain.advertisement.Advertisement
import com.example.realestate.domain.advertisement.repository.AdvertisementRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/advertisement")
class AdvertisementController (private val advertisementRepository: AdvertisementRepository, private val districtFinder: DistrictFinder){



    @GetMapping
    fun findAll(): List<Advertisement> {
        return advertisementRepository.findAll();
    }

    @GetMapping(path = ["/contains"])
    fun contains(): String {
        return districtFinder.getDistrictForCoordinates(52.2433436, 20.8985429)
    }

}