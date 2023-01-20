package com.example.realestate.domain.data.page_item

import java.math.BigDecimal

data class OtodomPageItemResponse(
    val props: Props?
) {

    fun getLatitude(): BigDecimal? {
        return this.props?.pageProps?.ad?.location?.coordinates?.latitude
    }

    fun getLongitude(): BigDecimal? {
        return this.props?.pageProps?.ad?.location?.coordinates?.longitude
    }

    fun getPrice(): BigDecimal? {
        return this.props?.pageProps?.ad?.target?.price
    }

    fun getPricePerMeter(): BigDecimal? {
        return this.props?.pageProps?.ad?.target?.pricePerMeter;
    }

    fun getArea(): BigDecimal? {
        return this.props?.pageProps?.ad?.target?.area
    }

    fun getTitle(): String? {
        return this.props?.pageProps?.ad?.title
    }
}
