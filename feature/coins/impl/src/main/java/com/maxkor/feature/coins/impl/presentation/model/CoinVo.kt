package com.maxkor.feature.coins.impl.presentation.model

data class CoinVo(
    val id: Int,
    val name: String,
    val price: String,
    val imageUrl: String,
    val isFavorite: Boolean,
)