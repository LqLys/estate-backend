package com.example.realestate.domain.advertisement.repository

import com.example.realestate.domain.advertisement.Advertisement
import jooq.tables.records.AdvertisementRecord
import org.jooq.DSLContext
import org.jooq.InsertOnDuplicateSetMoreStep


class AdvertisementRepositoryImpl(private val dslContext: DSLContext) : AdvertismentRepositoryFragment {


    override fun upsertAdvertisementByUniqueUrl(advertisement: Advertisement) {
        toRecord(advertisement).execute()

    }

    private fun toRecord(advertisement: Advertisement): InsertOnDuplicateSetMoreStep<AdvertisementRecord> {

        val advRecord: jooq.tables.Advertisement = jooq.tables.Advertisement.ADVERTISEMENT

        return dslContext.insertInto(
            advRecord,
            advRecord.ID,
            advRecord.URL,
            advRecord.PRICE,
            advRecord.PRICE_PER_METER,
            advRecord.AREA,
            advRecord.LATITUDE,
            advRecord.LONGITUDE,
            advRecord.DISTRICT,
            advRecord.DATA_SOURCE,
            advRecord.AD_NAME,
            advRecord.DISTANCE_TO_METRO,
            advRecord.STATUS
        ).values(advertisement.id,
            advertisement.url,
            advertisement.price,
            advertisement.pricePerMeter,
            advertisement.area,
            advertisement.latitude,
            advertisement.longitude,
            advertisement.district,
            advertisement.dataSource,
            advertisement.adName,
            advertisement.distanceToMetro,
            advertisement.status
        )
            .onConflict(advRecord.URL)
            .doUpdate()
            .set(advRecord.PRICE, advertisement.price)
            .set(advRecord.PRICE_PER_METER, advertisement.pricePerMeter)
            .set(advRecord.AREA, advertisement.area)
            .set(advRecord.LATITUDE, advertisement.latitude)
            .set(advRecord.LONGITUDE, advertisement.longitude)
            .set(advRecord.DISTRICT, advertisement.district)
            .set(advRecord.DATA_SOURCE, advertisement.dataSource)
            .set(advRecord.AD_NAME, advertisement.adName)
            .set(advRecord.DISTANCE_TO_METRO, advertisement.distanceToMetro)
    }
}