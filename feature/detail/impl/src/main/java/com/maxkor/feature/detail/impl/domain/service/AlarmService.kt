package com.maxkor.feature.detail.impl.domain.service

import com.maxkor.feature.detail.impl.domain.model.DetailCoin

interface AlarmService {

    fun createAlarm(
        detailCoin: DetailCoin,
        time: Long,
    )

    companion object {
        const val COIN_NAME_KEY = "coin_name"
        const val COIN_PRICE_KEY = "coin_price"
        const val COIN_IMAGE_URL_KEY = "coin_image_url"
    }
}