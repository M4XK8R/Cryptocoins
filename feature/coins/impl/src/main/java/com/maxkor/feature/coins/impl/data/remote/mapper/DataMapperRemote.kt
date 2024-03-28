package com.maxkor.feature.coins.impl.data.remote.mapper

import com.maxkor.feature.coins.impl.data.remote.model.CoinDto
import com.maxkor.feature.coins.impl.domain.model.Coin

fun CoinDto.toCoin() = Coin(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    isFavorite = false
)