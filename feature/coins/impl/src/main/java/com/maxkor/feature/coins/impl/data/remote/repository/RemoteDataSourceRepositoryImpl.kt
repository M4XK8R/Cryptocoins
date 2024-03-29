package com.maxkor.feature.coins.impl.data.remote.repository

import com.maxkor.core.base.utils.createDebugLog
import com.maxkor.feature.coins.impl.data.remote.api.CoinsApiService
import com.maxkor.feature.coins.impl.data.remote.mapper.toCoin
import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.domain.repository.RemoteDataSourceRepository
import javax.inject.Inject

class RemoteDataSourceRepositoryImpl @Inject constructor(
    private val coinsApi: CoinsApiService,
) : RemoteDataSourceRepository {

    override suspend fun getDataFromServer(): List<Coin>? {
        try {
            val response = coinsApi.getResponse()
            if (response.isSuccessful) {
                val coinsDtos = response.body()?.coinsInfo?.coins
                if (!coinsDtos.isNullOrEmpty()) {
                    return coinsDtos.map { it.toCoin() }
                } else {
                    createDebugLog("RemoteDataSourceRepositoryImpl: coinsDtos is null or empty")
                }
            } else {
                createDebugLog(
                    "RemoteDataSourceRepositoryImpl: response is not successful" +
                            response.message()
                )
            }
        } catch (exception: Exception) {
            createDebugLog("RemoteDataSourceRepositoryImpl: caught ${exception.message}")
            exception.printStackTrace()
        }
        return null
    }

}