package com.maxkor.feature.coins.impl.data.remote.api

import com.maxkor.feature.coins.impl.data.remote.model.CoinsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface CoinsApi {
    @Headers("x-access-token: $API_KEY")
    @GET("v2/coins")
    suspend fun getResponse(): Response<CoinsResponseDto>

    companion object {
        private const val API_KEY = "coinranking9b6a731a17c7fcfa9ff07829122ba58eed22a0d0806113ee"
        const val BASE_URL = "https://api.coinranking.com/"
    }
}