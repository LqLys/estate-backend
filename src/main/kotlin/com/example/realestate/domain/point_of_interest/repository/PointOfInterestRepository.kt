package com.example.realestate.domain.point_of_interest.repository

import com.example.realestate.domain.point_of_interest.PointOfInterest
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PointOfInterestRepository: JpaRepository<PointOfInterest, UUID> {
    fun findAllByType(type: String): List<PointOfInterest>
}