package com.maxkor.feature.coins.impl.domain.mapper

import com.maxkor.feature.coins.api.domain.model.FavoriteCoin
import com.maxkor.feature.coins.impl.domain.model.Coin

fun FavoriteCoin.toCoin() = Coin(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    isFavorite = false
)

fun Coin.toFavoriteCoin() = FavoriteCoin(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl
)