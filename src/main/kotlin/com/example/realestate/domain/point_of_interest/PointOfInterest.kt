package com.example.realestate.domain.point_of_interest

import com.example.realestate.domain.IdEntity
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "POINT_OF_INTEREST")
class PointOfInterest() : IdEntity() {


    @Column(name = "NAME")
    var name: String? = null

    @Column(name = "TYPE")
    var type: String? = null

    @Column(name = "LATITUDE", precision = 18, scale = 15)
    var latitude: BigDecimal? = null

    @Column(name = "LONGITUDE", precision = 18, scale = 15)
    var longitude: BigDecimal? = null

    constructor(
        name: String?,
        type: String?,
        latitude: BigDecimal?,
        longitude: BigDecimal?,
    ) : this() {
        this.name = name
        this.type = type
        this.latitude = latitude
        this.longitude = longitude
    }
}