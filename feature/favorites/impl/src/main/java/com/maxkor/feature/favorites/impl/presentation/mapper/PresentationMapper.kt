package com.maxkor.feature.favorites.impl.presentation.mapper

import com.maxkor.core.base.domain.model.Cryptocoin
import com.maxkor.core.base.presentation.model.CryptocoinVo
import com.maxkor.feature.favorites.impl.domain.model.FavoriteCoin
import com.maxkor.feature.favorites.impl.presentation.model.FavoriteCoinVo

fun FavoriteCoin.toFavoriteCoinVo() = FavoriteCoinVo(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl
)

fun Cryptocoin.toFavoriteCoinVo() = FavoriteCoinVo(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl
)

fun FavoriteCoinVo.toCryptocoin() = Cryptocoin(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    isFavorite = true
)

fun FavoriteCoinVo.toCryptocoinVo() = CryptocoinVo(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    isFavorite = true
)
