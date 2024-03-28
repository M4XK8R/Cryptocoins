package com.maxkor.feature.coins.impl.data.remote.repository

import com.maxkor.feature.coins.impl.data.remote.api.CoinsApiService
import com.maxkor.feature.coins.impl.data.remote.mapper.toCoin
import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.domain.repository.RemoteDataSourceRepository
import javax.inject.Inject

class RemoteDataSourceRepositoryImpl @Inject constructor(
    private val coinsApi: CoinsApiService,
) : RemoteDataSourceRepository {

    override suspend fun getDataFromServer(): List<Coin> {
        val response = coinsApi.getResponse()
        if (response.isSuccessful) {
            val coinsDtos = response.body()?.coinsInfo?.coins
            if (!coinsDtos.isNullOrEmpty()) {
                return coinsDtos.map { it.toCoin() }
            } else {
                throw Exception("RemoteDataSourceRepositoryImpl: coinsDtos is null or empty")
            }
        } else {
            throw Exception("RemoteDataSourceRepositoryImpl: response is not successful")
        }
    }
}