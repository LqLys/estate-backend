package com.example.realestate.domain

import org.locationtech.jts.geom.Polygon

data class DistrictPolygon(val districtName: String, val polygon: Polygon)
