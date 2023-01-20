package com.example.realestate.endpoint.advertisement

import com.example.realestate.domain.DistrictFinder
import com.example.realestate.domain.advertisement.Advertisement
import com.example.realestate.domain.advertisement.repository.AdvertisementRepository
import com.example.realestate.endpoint.advertisement.response.PricePerMeterRangeResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/api/v1/advertisement")
class AdvertisementController(
    private val advertisementRepository: AdvertisementRepository,
    private val districtFinder: DistrictFinder
) {


    @GetMapping
    fun findAll(): List<Advertisement> {
        return advertisementRepository.findAll();
    }

    @GetMapping(path = ["/contains"])
    fun contains(): String {
        return districtFinder.getDistrictForCoordinates(52.2433436, 20.8985429) ?: "not found"
    }

    @GetMapping(path = ["/filter"])
    fun findAllByDistrict(
        @RequestParam(value = "district") district: Set<String>,
        @RequestParam(value = "price") price: BigDecimal,
        @RequestParam(value = "ppm") ppm: BigDecimal,
        @RequestParam(value = "dtm") dtm: Int
    ): List<Advertisement> {
        return advertisementRepository.findAllByDistrictInAndPriceLessThanAndPricePerMeterLessThanAndDistanceToMetroLessThan(district, price, ppm, dtm);
    }

    @GetMapping(path = ["/ppm-range"])
    fun findMinAndMaxPricePerMeter(
        @RequestParam(value = "min") min: Int,
        @RequestParam(value = "max") max: Int
    ): PricePerMeterRangeResponse {
        val minPrice = advertisementRepository.findTopByPricePerMeterBetweenOrderByPricePerMeterAsc(
            BigDecimal(min),
            BigDecimal(max)
        )?.pricePerMeter
            ?: BigDecimal.ZERO;
        val maxPrice = advertisementRepository.findTopByPricePerMeterBetweenOrderByPricePerMeterDesc(
            BigDecimal(min),
            BigDecimal(max)
        )
            ?.pricePerMeter ?: BigDecimal.ZERO;

        return PricePerMeterRangeResponse(minPrice, maxPrice)
    }

}