package com.maxkor.feature.coins.impl.data.repository

import com.maxkor.core.base.utils.createDebugLog
import com.maxkor.feature.coins.impl.data.local.dao.CoinsDao
import com.maxkor.feature.coins.impl.data.local.mapper.toCoin
import com.maxkor.feature.coins.impl.data.local.mapper.toCoinEntity
import com.maxkor.feature.coins.impl.data.remote.api.CoinsApi
import com.maxkor.feature.coins.impl.data.remote.mapper.toCoin
import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoinsRepositoryImpl @Inject constructor(
    private val dao: CoinsDao,
    private val api: CoinsApi,
) : CoinsRepository {

    override fun getCoinsFlow(): Flow<List<Coin>> = dao
        .getAllFlow()
        .map { coinsEntities ->
            coinsEntities.map { it.toCoin() }
        }

    override suspend fun getCoins(): List<Coin> = dao
        .getAll()
        .map { it.toCoin() }

    override suspend fun getCoinById(id: Int): Coin = dao
        .getById(id)
        .toCoin()

    override suspend fun updateCoin(coin: Coin) = dao
        .update(coin.toCoinEntity())

    override suspend fun updateCoins(coins: List<Coin>) = dao
        .update(
            coins.map { it.toCoinEntity() }
        )

    override suspend fun getCoinsFromServer(): List<Coin>? {
        try {
            val response = api.getResponse()
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