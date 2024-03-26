package com.maxkor.feature.coins.impl.data.local.mapper

import com.maxkor.feature.coins.impl.data.local.model.CryptocoinEntity
import com.maxkor.feature.coins.impl.domain.model.Cryptocoin

fun Cryptocoin.toCryptocoinEntity() = CryptocoinEntity(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    isFavorite = isFavorite
)

fun CryptocoinEntity.toCryptocoin() = Cryptocoin(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    isFavorite = isFavorite
)
