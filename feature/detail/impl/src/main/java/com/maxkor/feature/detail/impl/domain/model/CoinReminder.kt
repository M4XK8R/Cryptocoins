package com.maxkor.feature.detail.impl.domain.model

data class CoinReminder(
    val detailCoin: DetailCoin,
    val hour: Int,
    val minute: Int,
)