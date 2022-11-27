package com.example.realestate.domain.data.page_item

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class Target(
    @JsonProperty("Area") val area: String?,
    @JsonProperty("AreaRange") val areaRang: List<String>?,
    @JsonProperty("Build_year") val buildYear: String?,
    @JsonProperty("Building_floors_num") val buildingFloorsNum: String?,
    @JsonProperty("Building_ownership") val building_ownership: List<String>?,
    @JsonProperty("City") val city: String?,
    @JsonProperty("Price_per_m") val pricePerMeter: Int?,
    @JsonProperty("Rent") val rent: Int?,
    @JsonProperty("Price") val price: BigDecimal?,
    @JsonProperty("Rooms_num") val roomsNum: List<String>?
)
