package com.example.realestate

import com.example.realestate.config.PolygonConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication


@SpringBootApplication
class RealEstateApplication
fun main(args: Array<String>) {
    runApplication<RealEstateApplication>(*args)
}
