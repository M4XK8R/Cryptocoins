package com.maxkor.feature.favorites.impl.presentation.mapper

import com.maxkor.feature.coins.api.domain.model.FavoriteCoin
import com.maxkor.feature.favorites.impl.presentation.model.FavoriteCoinVo

fun FavoriteCoin.toFavoriteCoinVo() = FavoriteCoinVo(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl
)

fun FavoriteCoinVo.toFavoriteCoin() = FavoriteCoin(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl
)