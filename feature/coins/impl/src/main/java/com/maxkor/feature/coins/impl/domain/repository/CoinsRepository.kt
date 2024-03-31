package com.maxkor.feature.coins.impl.domain.repository

import com.maxkor.feature.coins.impl.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinsRepository {

    fun getCoinsFlow(): Flow<List<Coin>>

    suspend fun getCoins(): List<Coin>

    suspend fun getCoinById(id: Int): Coin

    suspend fun updateCoin(coin: Coin)

    suspend fun updateCoins(coins: List<Coin>)

    suspend fun getCoinsFromServer(): List<Coin>?
}