package com.maxkor.feature.coins.impl.presentation.mapper

import com.maxkor.feature.coins.impl.domain.model.Cryptocoin
import com.maxkor.feature.coins.impl.presentation.model.CryptocoinVo

fun Cryptocoin.toCryptocoinVo(): CryptocoinVo =
    CryptocoinVo(
        id = id,
        name = name,
        price = price,
        imageUrl = imageUrl,
        isFavorite = isFavorite
    )