package com.maxkor.feature.coins.impl.data.local.mapper

import com.maxkor.feature.coins.impl.data.local.model.CoinCached
import com.maxkor.feature.coins.impl.data.local.model.CoinEntity
import com.maxkor.feature.coins.impl.domain.model.Coin

fun Coin.toCoinEntity() = CoinEntity(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    isFavorite = isFavorite
)

fun CoinEntity.toCoin() = Coin(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    isFavorite = isFavorite
)
