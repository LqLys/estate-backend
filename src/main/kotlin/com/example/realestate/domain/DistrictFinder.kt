package com.example.realestate.domain


import com.example.realestate.config.PolygonConfig
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.Geometry
import org.springframework.stereotype.Service

@Service
class DistrictFinder(
    private val polygonConfig: PolygonConfig
) {

    fun getDistrictForCoordinates(latitude: Double, longitude: Double): String? {
        val pointToTest: Geometry = polygonConfig.getGeometryFactory().createPoint(Coordinate(latitude, longitude))
        val districtPolygons: List<DistrictPolygon> = polygonConfig.districtPolygons()
        val districtContainingPoint = districtPolygons.find { it.polygon.contains(pointToTest) }
        return districtContainingPoint?.districtName.orEmpty()
    }
}