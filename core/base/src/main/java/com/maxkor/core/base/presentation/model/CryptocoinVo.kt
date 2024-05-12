package com.maxkor.core.base.presentation.model

data class CryptocoinVo(
    val id: Int,
    val name: String,
    val price: String,
    val imageUrl: String,
    val isFavorite: Boolean,
)