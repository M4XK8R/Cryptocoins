package com.maxkor.feature.coins.impl.data.repository

import com.maxkor.feature.coins.impl.data.util.TokenBucket
import android.content.Context
import com.maxkor.core.base.util.createDebugLog
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import javax.inject.Inject

class CoinsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dao: CoinsDao,
    private val api: CoinsApi,
    private val tokenBucket: TokenBucket,
) : CoinsRepository {

    private var firsTimeCoinsLoading: Boolean = true

    private val _coinsFlow: MutableStateFlow<List<Coin>> =
        MutableStateFlow(emptyList())

    override val coinsFlow: Flow<List<Coin>>
        get() = _coinsFlow

    override suspend fun saveCoinsToDatabase() {
        val coins = _coinsFlow.value
        if (coins.isNotEmpty()) {
            dao.insertOrUpdate(
                coins.map { it.toCoinEntity() }
            )
        }
    }

    override suspend fun updateCoin(coin: Coin) {
        val isCoinOnList = _coinsFlow.value.find { it.name == coin.name } != null
        if (isCoinOnList) {
            _coinsFlow.getAndUpdate { coins ->
                coins.map { currentCoin ->
                    if (currentCoin.name == coin.name) coin else currentCoin
                }
            }
        }
    }

    override suspend fun downloadAndUpdateCoins(
        hasInternetConnection: Boolean,
        informUserOnFailure: (String) -> Unit,
    ) {
        if (tokenBucket.tryAcquire()) {
            val coins: MutableList<Coin> = mutableListOf()

            if (firsTimeCoinsLoading) {
                val coinsFromDataBase = getCoinsFromDatabase()
                if (coinsFromDataBase.isNotEmpty()) {
                    _coinsFlow.value = coinsFromDataBase
                    coins.addAll(coinsFromDataBase)
                }
                firsTimeCoinsLoading = false
            } else {
                if (_coinsFlow.value.isNotEmpty()) {
                    coins.addAll(_coinsFlow.value)
                }
            }

            if (hasInternetConnection) {
                val coinsFromServer = downloadCoins(
                    informUserOnFailure = informUserOnFailure
                )
                if (coinsFromServer.isNotEmpty()) {
                    if (coins.isNotEmpty()) {
                        val parsedCoins = parseCoins(
                            newCoins = coinsFromServer,
                            currentCoins = coins
                        )
                        _coinsFlow.value = parsedCoins
                    } else {
                        _coinsFlow.value = coinsFromServer
                    }
                }
            } else {
                informUserOnFailure(
                    context.getString(
                        com.maxkor.core.base.R.string.no_internet_connection_warning
                    )
                )
            }
        }
    }

    /**
     * Private sector
     */
    private suspend fun getCoinsFromDatabase(): List<Coin> = dao
        .getAll()
        .map { it.toCoin() }

    private fun parseCoins(
        newCoins: List<Coin>,
        currentCoins: List<Coin>,
    ): List<Coin> {
        val parsedCoins = mutableListOf<Coin>()
        newCoins.forEach { updatedCoin ->
            val currentCoin = currentCoins.first { currentCoin ->
                currentCoin.id == updatedCoin.id
            }
            val parsedCoin = updatedCoin.copy(
                isFavorite = currentCoin.isFavorite
            )
            parsedCoins.add(parsedCoin)
        }
        return parsedCoins.toList()
    }

    private suspend fun downloadCoins(
        informUserOnFailure: (String) -> Unit,
    ): List<Coin> {
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
        return emptyList()
    }
}