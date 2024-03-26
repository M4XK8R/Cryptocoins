package com.maxkor.feature.coins.impl.domain.repository

import com.maxkor.feature.coins.impl.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface LocalDataSourceRepository {

    suspend fun getCoins(): List<Coin>

    suspend fun getCoinById(id: Int): Coin

    suspend fun addCoins(coins: List<Coin>)

    suspend fun changeCoinFavoriteState(coin: Coin)

    suspend fun updateDatabaseData(coins: List<Coin>)

    fun getCoinsFlow(): Flow<List<Coin>>
}