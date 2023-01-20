package com.example.realestate.domain

import org.geotools.geometry.jts.JTS
import org.geotools.referencing.CRS
import org.locationtech.jts.geom.Coordinate
import org.opengis.referencing.FactoryException
import org.opengis.referencing.operation.TransformException
import org.springframework.stereotype.Service

@Service
class DistanceCalculator() {


    @Throws(FactoryException::class, TransformException::class)
    fun distance(
        startLat: Double,
        startLong: Double,
        endLat: Double,
        endLong: Double
    ): Double {
        val sourceCRS = CRS.decode("EPSG:4326")
        val targetCRS = CRS.decode("EPSG:3857")
        val transform = CRS.findMathTransform(sourceCRS, targetCRS)
        val start = Coordinate(startLong, startLat)
        val end = Coordinate(endLong, endLat)
        JTS.transform(start, start, transform)
        JTS.transform(end, end, transform)
        return start.distance(end)
    }

}
