package com.example.realestate.domain

import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
data class DistrictPolygonSource(val districtName: String, val fileName: String)


