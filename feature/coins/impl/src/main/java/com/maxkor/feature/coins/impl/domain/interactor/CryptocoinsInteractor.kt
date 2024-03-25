package com.maxkor.feature.coins.impl.domain.interactor

import com.maxkor.feature.coins.impl.domain.model.Cryptocoin
import kotlinx.coroutines.flow.Flow

interface CryptocoinsInteractor {

    suspend fun getCoins(): List<Cryptocoin>

    suspend fun getCoinById(id: Int): Cryptocoin

    suspend fun addCoins(coins: List<Cryptocoin>)

    suspend fun changeCoinFavoriteState(cryptocoin: Cryptocoin)

    suspend fun updateDatabaseData(cryptocoins: List<Cryptocoin>)

    fun getCoinsFlow(): Flow<List<Cryptocoin>>
}