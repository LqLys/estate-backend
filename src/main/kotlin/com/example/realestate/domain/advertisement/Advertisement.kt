package com.example.realestate.domain.advertisement

import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "ADVERTISEMENT")
class Advertisement {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    val id: UUID? = null

    @Column(name = "URL", nullable = false, unique = true)
    var url: String? = null

    @Column(name = "PRICE", precision = 11, scale = 2)
    var price: BigDecimal? = null

    @Column(name = "PRICE_PER_METER", precision = 10, scale = 2)
    var pricePerMeter: BigDecimal? = null

    @Column(name = "AREA", precision = 6, scale = 2)
    var area: BigDecimal? = null

    @Column(name = "LATITUDE", precision = 18, scale = 15)
    var latitude: BigDecimal? = null

    @Column(name = "LONGITUDE", precision = 18, scale = 15)
    var longitude: BigDecimal? = null

    @Column(name = "DISTRICT")
    var district: String? = null

    @Column(name = "DATA_SOURCE")
    var dataSource: String? = null

    @Column(name = "AD_NAME")
    var adName: String? = null





    constructor()
    constructor(
        url: String?,
        price: BigDecimal?,
        pricePerMeter: BigDecimal?,
        area: BigDecimal?,
        latitude: BigDecimal?,
        longitude: BigDecimal?,
        district: String?,
        dataSource: String?,
        adName: String?
    ) {
        this.url = url
        this.price = price
        this.pricePerMeter = pricePerMeter
        this.area = area
        this.latitude = latitude
        this.longitude = longitude
        this.district = district
        this.dataSource = dataSource
        this.adName = adName
    }


}