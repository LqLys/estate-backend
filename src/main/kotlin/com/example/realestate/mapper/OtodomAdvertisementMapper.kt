package com.example.realestate.mapper

import com.example.realestate.domain.DistanceCalculator
import com.example.realestate.domain.DistrictFinder
import com.example.realestate.domain.advertisement.Advertisement
import com.example.realestate.domain.data.page_item.OtodomPageItemResponse
import com.example.realestate.domain.point_of_interest.PointOfInterest
import com.example.realestate.domain.point_of_interest.repository.PointOfInterestRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal
import javax.annotation.PostConstruct

@Service
class OtodomAdvertisementMapper(
    private val districtFinder: DistrictFinder, private val distanceCalculator: DistanceCalculator,
    private val pointOfInterestRepository: PointOfInterestRepository
) {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
        private var metroStations: List<PointOfInterest> = listOf();
    }


    fun itemResponseToAdvertisement(
        advertisementData: OtodomPageItemResponse,
        advertisementUrl: String
    ): Advertisement {
        val latitude = advertisementData.getLatitude()
        val longitude = advertisementData.getLongitude()
        val district = getDistrict(latitude, longitude);
        val distanceToMetro = getDistanceToMetro(latitude, longitude);

        return Advertisement(
            advertisementUrl,
            advertisementData.getPrice(),
            advertisementData.getPricePerMeter(),
            advertisementData.getArea(),
            latitude,
            longitude,
            district,
            "OTODOM",
            advertisementData.getTitle(),
            distanceToMetro
        )
    }

    private fun getDistanceToMetro(pointLatitude: BigDecimal?, pointLongitude: BigDecimal?): Int {
        if (pointLatitude == null || pointLongitude == null) {
            return Int.MAX_VALUE
        }

        val distanceToClosestStation = metroStations.minOfOrNull { station ->
            distanceCalculator.distance(
                station.latitude!!.toDouble(), station.longitude!!.toDouble(),
                pointLatitude.toDouble(), pointLongitude.toDouble()
            )
        }!!.toInt();

        return distanceToClosestStation;

    }


    private fun getDistrict(latitude: BigDecimal?, longitude: BigDecimal?): String? {
        return if (latitude != null && longitude != null) {
            try {
                districtFinder.getDistrictForCoordinates(latitude.toDouble(), longitude.toDouble())
            } catch (e: Exception) {
                log.error(
                    "Error getting district for lat: $latitude, lon: $longitude",
                    e
                )
                "ERROR"
            }
        } else {
            null
        }
    }

    @PostConstruct
    fun loadMetroStations() {
        metroStations = pointOfInterestRepository.findAllByType("METRO");
    }

}