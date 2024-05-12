package com.maxkor.feature.coins.impl.data.remote.mapper

import com.maxkor.feature.coins.impl.data.remote.model.CoinDto
import com.maxkor.feature.coins.impl.domain.model.Coin
import kotlin.math.roundToInt

fun CoinDto.toCoin() = Coin(
    id = id,
    name = name,
    price = formatPrice(price),
    imageUrl = imageUrl,
    isFavorite = false
)

private fun formatPrice(price: String): String =
    ((price.toDouble() * 100).roundToInt() / 100.0).toString() + "$"
