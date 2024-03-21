package com.maxkor.feature.coins.impl.domain.model

data class Cryptocoin(
    val id: Int,
    val name: String,
    val price: String,
    val imageUrl: String,
    var isFavorite: Boolean
)
