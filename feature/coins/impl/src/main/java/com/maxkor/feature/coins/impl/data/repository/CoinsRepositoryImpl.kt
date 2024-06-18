package com.maxkor.feature.coins.impl.data.repository

import android.content.Context
import com.maxkor.feature.coins.impl.R
import com.maxkor.feature.coins.impl.data.local.cache.CoinsCache
import com.maxkor.feature.coins.impl.data.local.dao.CoinsDao
import com.maxkor.feature.coins.impl.data.remote.api.CoinsApi
import com.maxkor.feature.coins.impl.data.remote.mapper.toCoin
import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.domain.repository.CoinsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoinsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dao: CoinsDao,
    private val api: CoinsApi,
    private val cache: CoinsCache,
) : CoinsRepository {

    private val _coinsFlow: MutableStateFlow<List<Coin>> =
        MutableStateFlow(emptyList())

    override val coinsFlow: Flow<List<Coin>>
        get() = _coinsFlow

    override fun getCoinByNameFlow(name: String): Flow<Coin> =
        _coinsFlow.map { coins ->
            coins.first { it.name == name }
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
        if (hasInternetConnection) {
            val newCoins = downloadCoins(
                informUserOnFailure = informUserOnFailure
            )
            if (newCoins.isNullOrEmpty()) {
                return
            }
            val currentCoins = _coinsFlow.value
            if (currentCoins.isEmpty()) {
                _coinsFlow.value = newCoins
            } else {
                val parsedCoins = parseCoins(
                    updatedCoins = newCoins,
                    currentCoins = currentCoins
                )
                _coinsFlow.value = parsedCoins
            }
        } else {
            informUserOnFailure(
                context.getString(
                    com.maxkor.core.base.R.string.no_internet_connection_warning
                )
            )
        }
    }

    /**
     * Private sector
     */
    private fun parseCoins(
        updatedCoins: List<Coin>,
        currentCoins: List<Coin>,
    ): List<Coin> {
        val parsedCoins = mutableListOf<Coin>()
        updatedCoins.forEach { updatedCoin ->
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