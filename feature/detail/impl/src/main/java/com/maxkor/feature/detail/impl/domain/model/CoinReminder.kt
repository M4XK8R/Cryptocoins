package com.maxkor.feature.detail.impl.domain.model

import com.maxkor.feature.coins.api.domain.model.DetailCoin

data class CoinReminder(
    val detailCoin: DetailCoin,
    val hour: Int,
    val minute: Int,
)