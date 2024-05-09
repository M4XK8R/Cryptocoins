package com.maxkor.feature.coins.impl.domain.mapper

import com.maxkor.core.base.domain.model.Cryptocoin
import com.maxkor.feature.coins.impl.domain.model.Coin

fun Cryptocoin.toCoin() = Coin(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    isFavorite = false
)

fun Coin.toCryptocoin() = Cryptocoin(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    isFavorite = isFavorite
)
