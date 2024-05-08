package com.maxkor.feature.detail.impl.domain.model

import com.maxkor.feature.detail.api.domain.model.DetailCoin

data class CoinReminder(
    val detailCoin: DetailCoin,
    val hour: Int,
    val minute: Int,
)