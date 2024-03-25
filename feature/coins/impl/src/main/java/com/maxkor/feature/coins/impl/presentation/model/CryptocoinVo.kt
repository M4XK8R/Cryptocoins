package com.maxkor.feature.coins.impl.presentation.model

data class CryptocoinVo(
    val id: Int,
    val name: String,
    val price: String,
    val imageUrl: String,
    var isFavorite: Boolean,
)