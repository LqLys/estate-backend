package com.example.realestate.domain.advertisement.repository

import com.example.realestate.domain.advertisement.Advertisement

interface AdvertismentRepositoryFragment {
    fun upsertAdvertisementByUniqueUrl(advertisement: Advertisement)
}