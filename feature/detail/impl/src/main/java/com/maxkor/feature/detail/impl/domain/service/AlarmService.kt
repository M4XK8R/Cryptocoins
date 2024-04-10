package com.maxkor.feature.detail.impl.domain.service

interface AlarmService {

    fun createAlarm(
        coinName: String,
        coinPrice: String,
        coinImageUrl: String,
        time: Long,
    )

    companion object {
        const val COIN_NAME_KEY = "coin_name"
        const val COIN_PRICE_KEY = "coin_price"
        const val COIN_IMAGE_URL_KEY = "coin_image_url"
    }
}