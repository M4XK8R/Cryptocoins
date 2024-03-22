package com.maxkor.feature.coins.impl.domain.mapper

import com.maxkor.feature.coins.api.domain.model.CryptocoinFav
import com.maxkor.feature.coins.impl.domain.model.Cryptocoin

internal fun Cryptocoin.toCryptocoinFav(): CryptocoinFav =
    CryptocoinFav(
        id = id,
        name = name,
        price = price,
        imageUrl = imageUrl,
        isFavorite = isFavorite
    )

internal fun CryptocoinFav.toCryptocoin(): Cryptocoin =
    Cryptocoin(
        id = id,
        name = name,
        price = price,
        imageUrl = imageUrl,
        isFavorite = isFavorite
    )