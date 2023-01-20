package com.example.realestate.domain.data

data class OtodomPageResponse(val props: Props?){

    fun getSlugsFromPage(): List<String> {
        return this.props
            ?.pageProps
            ?.data
            ?.searchAds
            ?.items
            ?.mapNotNull { it.slug }
            .orEmpty()
    }
}
