package com.maxkor.feature.coins.impl.data.mapper

import com.maxkor.database.local.intermediarycoins.entity.CryptocoinEntity
import com.maxkor.feature.coins.impl.domain.model.Cryptocoin

fun CryptocoinEntity.toCryptocoin(): Cryptocoin =
    Cryptocoin(
        id = id,
        name = name,
        price = price,
        imageUrl = imageUrl,
        isFavorite = isFavorite
    )