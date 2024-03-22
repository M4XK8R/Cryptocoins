package com.maxkor.feature.coins.api.domain.model

data class CryptocoinFav(
    val id: Int,
    val name: String,
    val price: String,
    val imageUrl: String,
    var isFavorite: Boolean,
)
