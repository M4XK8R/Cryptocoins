package com.maxkor.feature.coins.impl.presentation.mapper

import com.maxkor.core.base.presentation.model.CryptocoinVo
import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.presentation.model.CoinVo

fun Coin.toCoinVo() = CoinVo(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    isFavorite = isFavorite
)

fun CoinVo.toCoin() = Coin(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    isFavorite = isFavorite
)

fun CoinVo.toCryptocoinVo() = CryptocoinVo(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    isFavorite = isFavorite
)