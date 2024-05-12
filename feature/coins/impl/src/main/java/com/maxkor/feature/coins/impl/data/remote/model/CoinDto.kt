package com.maxkor.feature.coins.impl.data.remote.model

import com.squareup.moshi.Json

data class CoinDto(

    @Json(name = "rank")
    val id: Int,

    @Json(name = "symbol")
    val name: String,

    @Json(name = "price")
    val price: String,

    @Json(name = "iconUrl")
    val imageUrl: String,
)


