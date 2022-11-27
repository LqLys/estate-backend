package com.example.realestate.domain.advertisement.repository

import com.example.realestate.domain.advertisement.Advertisement
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AdvertisementRepository : JpaRepository<Advertisement,  UUID>{
}