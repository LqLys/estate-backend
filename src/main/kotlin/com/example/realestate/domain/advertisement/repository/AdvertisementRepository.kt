package com.example.realestate.domain.advertisement.repository

import com.example.realestate.domain.advertisement.Advertisement
import org.springframework.data.jpa.repository.JpaRepository
import java.math.BigDecimal
import java.util.*

interface AdvertisementRepository : JpaRepository<Advertisement,  UUID>, AdvertismentRepositoryFragment{
    fun findByUrl(url: String): Advertisement?
    fun findAllByDistrictInAndPriceLessThanAndPricePerMeterLessThanAndDistanceToMetroLessThan(district: Set<String>, price: BigDecimal, ppm: BigDecimal, dtm: Int): List<Advertisement>
    fun findTopByPricePerMeterBetweenOrderByPricePerMeterAsc(min: BigDecimal, max: BigDecimal): Advertisement?;
    fun findTopByPricePerMeterBetweenOrderByPricePerMeterDesc(min: BigDecimal, max: BigDecimal): Advertisement?;
}