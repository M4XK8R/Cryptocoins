package com.maxkor.feature.coins.impl.domain.mapper

import com.maxkor.feature.coins.api.domain.model.FavoriteCoin
import com.maxkor.feature.coins.impl.domain.model.Coin

fun Coin.toCoinFav() = FavoriteCoin(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl
)

fun FavoriteCoin.toCoin() = Coin(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    isFavorite = true
)