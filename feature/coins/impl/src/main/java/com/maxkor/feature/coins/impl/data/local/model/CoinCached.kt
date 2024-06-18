package com.maxkor.feature.coins.impl.data.local.model

data class CoinCached(
    val id: Int,
    val name: String,
    val price: String,
    val imageUrl: String,
    val isFavorite: Boolean,
)
