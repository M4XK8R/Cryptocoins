package com.maxkor.feature.coins.impl.data.local.mapper

import com.maxkor.feature.coins.impl.data.local.model.CryptocoinEntity
import com.maxkor.feature.coins.impl.domain.model.Cryptocoin

fun CryptocoinEntity.toCryptocoin(): Cryptocoin =
    Cryptocoin(
        id = id,
        name = name,
        price = price,
        imageUrl = imageUrl,
        isFavorite = isFavorite
    )

fun Cryptocoin.toCryptocoinEntity(): CryptocoinEntity =
    CryptocoinEntity(
        id = id,
        name = name,
        price = price,
        imageUrl = imageUrl,
        isFavorite = isFavorite
    )
