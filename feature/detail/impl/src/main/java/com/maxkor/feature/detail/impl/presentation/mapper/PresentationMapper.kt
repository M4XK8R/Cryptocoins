package com.maxkor.feature.detail.impl.presentation.mapper

import com.maxkor.core.base.domain.model.Cryptocoin
import com.maxkor.feature.detail.impl.domain.model.DetailCoin
import com.maxkor.feature.detail.impl.presentation.model.DetailCoinVo

fun DetailCoin.toDetailCoinVo() = DetailCoinVo(
    name = name,
    price = price,
    imageUrl = imageUrl,
    extraInfo = extraInfo
)

fun DetailCoinVo.toDetailCoin() = DetailCoin(
    id = 0,
    name = name,
    price = price,
    imageUrl = imageUrl
)

fun Cryptocoin.toDetailCoinVo() = DetailCoinVo(
    name = name,
    price = price,
    imageUrl = imageUrl
)