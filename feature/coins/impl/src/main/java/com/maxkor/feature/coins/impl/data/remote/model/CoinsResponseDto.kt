package com.maxkor.feature.coins.impl.data.remote.model

import com.squareup.moshi.Json

data class CoinsResponseDto(
    val status: String,

    @Json(name = "data")
    val coinsInfo: CoinsInfoDto,
)

