package com.example.realestate.domain


import com.example.realestate.config.PolygonConfig
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.PrecisionModel
import org.springframework.stereotype.Service

@Service
class DistrictFinder(
    private val polygonConfig: PolygonConfig
) {

    fun getDistrictForCoordinates(latitude: Double, longitude: Double): String? {
        val pm = PrecisionModel(PrecisionModel.maximumPreciseValue)
        val gf = GeometryFactory(pm)
        val testPoint: Geometry = gf.createPoint(Coordinate(latitude, longitude))
        val districtPolygons: List<DistrictPolygon> = polygonConfig.files()
        val districtContainingPoint = districtPolygons.find { it.polygon.contains(testPoint) }

        return districtContainingPoint?.districtName
    }
}