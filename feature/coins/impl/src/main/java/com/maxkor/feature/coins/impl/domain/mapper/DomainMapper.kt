package com.maxkor.feature.coins.impl.domain.mapper

import com.maxkor.feature.coins.api.domain.model.CryptocoinFav
import com.maxkor.feature.coins.impl.domain.model.Cryptocoin

fun Cryptocoin.toCryptocoinFav() = CryptocoinFav(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    isFavorite = isFavorite
)

fun CryptocoinFav.toCryptocoin() = Cryptocoin(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    isFavorite = isFavorite
)