package com.maxkor.feature.coins.impl.domain.repository

import com.maxkor.feature.coins.impl.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinsRepository {

    fun getCoinByNameFlow(name: String): Flow<Coin>

    fun getCoinsFlow(): Flow<List<Coin>>

    suspend fun updateCoin(coin: Coin)

    suspend fun downloadAndUpdateCoins(
        hasInternetConnection: Boolean,
        informUserOnFailure: (String) -> Unit,
    )
}