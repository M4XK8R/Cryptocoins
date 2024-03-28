package com.maxkor.feature.coins.impl.data.remote.api

import com.maxkor.feature.coins.impl.data.remote.model.CoinsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

private const val API_KEY = "coinranking9b6a731a17c7fcfa9ff07829122ba58eed22a0d0806113ee"

interface CoinsApiService {
    @Headers("x-access-token: $API_KEY")
    @GET("v2/coins")
    suspend fun getResponse(): Response<CoinsResponseDto>
}