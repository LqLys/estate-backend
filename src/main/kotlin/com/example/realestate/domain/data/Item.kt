package com.example.realestate.domain.data

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class Item(
    val id: Long?,
    val title: String?,
    val slug: String?,
    val  estate: String?,
    val  transaction: String?,
    val  locationLabel: LocationLabel?,
    val  images: List<Image>?,
    val  isExclusiveOffer: Boolean?,
    val  isPrivateOwner: Boolean?,
    val  isPromoted: Boolean?,
    val  agency: Agency?,
    val  openDays: String?,
    val  totalPrice: Money?,
    val  priceFromPerSquareMeter: Money?,
    val  pricePerSquareMeter: Money?,
    val  areaInSquareMeters: Int?,
    val  terrainAreaInSquareMeters: Int?,
    val  roomsNumber: String?,
    val  hidePrice: Boolean?,
    val  investmentState: Any?,
    val  investmentUnitsAreaInSquareMeters: Any?,
    val  peoplePerRoom: Any?,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val  dateCreated: LocalDateTime?,
    val  investmentUnitsNumber: Any?,
    val  investmentUnitsRoomsNumber: Any?,
    val  investmentEstimatedDelivery: Any?,
    val  __typename: String?
)
