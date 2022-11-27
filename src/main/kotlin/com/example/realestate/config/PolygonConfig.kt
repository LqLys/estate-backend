package com.example.realestate.config

import com.example.realestate.domain.DistrictPolygon
import com.example.realestate.domain.DistrictPolygonSource
import com.example.realestate.domain.GeoJsonCoordinates
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.geotools.geometry.jts.JTSFactoryFinder
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ResourceLoader

@Configuration
@ConfigurationProperties(prefix = "application.polygons")
class PolygonConfig (private val resourceLoader: ResourceLoader, private val objectMapper: ObjectMapper){

    var districts: List<DistrictPolygonSource> = listOf()

    @Bean
    fun files(): List<DistrictPolygon> {
        val geometryFactory: GeometryFactory = JTSFactoryFinder.getGeometryFactory()
        return districts.map { DistrictPolygon(it.districtName, geometryFactory.createPolygon(buildDistrictPolygons(it))) }
            .toList()
    }

    private fun buildDistrictPolygons(source: DistrictPolygonSource): Array<Coordinate>{
         val coords: GeoJsonCoordinates = resourceLoader.getResource("classpath:${source.fileName}").inputStream
            .let { objectMapper.readValue(it) }
        return coords.coordinates[0][0].map { Coordinate(it[1], it[0]) }.toTypedArray();
    }




}