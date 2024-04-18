package com.maxkor.core.base.presentation.mapper

import com.maxkor.core.base.domain.model.Cryptocoin
import com.maxkor.core.base.presentation.model.CryptocoinVo

fun Cryptocoin.toCryptocoinVo() = CryptocoinVo(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    isFavorite = isFavorite
)