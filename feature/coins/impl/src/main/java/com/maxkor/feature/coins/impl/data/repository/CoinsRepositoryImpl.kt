package com.maxkor.feature.coins.impl.data.repository

import android.content.Context
import com.maxkor.feature.coins.impl.R
import com.maxkor.feature.coins.impl.data.local.dao.CoinsDao
import com.maxkor.feature.coins.impl.data.local.mapper.toCoin
import com.maxkor.feature.coins.impl.data.local.mapper.toCoinEntity
import com.maxkor.feature.coins.impl.data.remote.api.CoinsApi
import com.maxkor.feature.coins.impl.data.remote.mapper.toCoin
import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.domain.repository.CoinsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoinsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dao: CoinsDao,
    private val api: CoinsApi,
) : CoinsRepository {

    override fun getCoinsFlow(): Flow<List<Coin>> = dao
        .getAllFlow()
        .map { coinsEntities ->
            coinsEntities.map { it.toCoin() }
        }

    override fun getCoinByIdFlow(id: Int): Flow<Coin> = dao
        .getByIdFlow(id)
        .map { it.toCoin() }

    override suspend fun getCoins(): List<Coin> = dao
        .getAll()
        .map { it.toCoin() }

    override suspend fun getCoinById(id: Int): Coin = dao
        .getById(id)
        .toCoin()

    override suspend fun getCoinByName(name: String): Coin = dao
        .getByName(name)
        .toCoin()

    override suspend fun updateCoin(coin: Coin) = dao
        .update(coin.toCoinEntity())

    override suspend fun updateCoins(coins: List<Coin>) = dao
        .update(
            coins.map { it.toCoinEntity() }
        )

    override suspend fun getCoinsFromServer(
        informUserOnFailure: (String) -> Unit,
    ): List<Coin>? {
        try {
            val response = api.getResponse()
            if (response.isSuccessful) {
                val coinsDtos = response.body()?.coinsInfo?.coins
                if (!coinsDtos.isNullOrEmpty()) {
                    return coinsDtos.map { it.toCoin() }
                } else {
                    informUserOnFailure(
                        context.getString(
                            R.string.data_from_server_is_null_or_empty
                        )
                    )
                }
            } else {
                informUserOnFailure(
                    context.getString(
                        R.string.response_is_not_successful
                    )
                )
            }
        } catch (exception: Exception) {
            informUserOnFailure(
                context.getString(
                    R.string.load_data_error
                )
            )
        }
        return null
    }
}