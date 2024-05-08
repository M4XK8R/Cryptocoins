package com.maxkor.feature.coins.impl.domain.repository

import com.maxkor.feature.coins.impl.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinsRepository {

    fun getCoinsFlow(): Flow<List<Coin>>

    fun getCoinByIdFlow(id: Int): Flow<Coin>

    suspend fun getCoins(): List<Coin>

    suspend fun getCoinById(id: Int): Coin

    suspend fun getCoinByName(name: String): Coin

    suspend fun updateCoin(coin: Coin)

    suspend fun updateCoins(coins: List<Coin>)

    suspend fun getCoinsFromServer(
        informUserOnFailure: (String) -> Unit,
    ): List<Coin>?
}