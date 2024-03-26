package com.maxkor.feature.coins.impl.domain.mapper

import com.maxkor.feature.coins.api.domain.model.CoinFav
import com.maxkor.feature.coins.impl.domain.model.Coin

fun Coin.toCoinFav() = CoinFav(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    isFavorite = isFavorite
)

fun CoinFav.toCoin() = Coin(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    isFavorite = isFavorite
)