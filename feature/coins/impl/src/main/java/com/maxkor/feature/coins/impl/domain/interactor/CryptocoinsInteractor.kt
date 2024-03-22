package com.maxkor.feature.coins.impl.domain.interactor

import com.maxkor.feature.coins.impl.domain.model.Cryptocoin
import kotlinx.coroutines.flow.Flow

internal interface CryptocoinsInteractor {

    suspend fun getCoins(): List<Cryptocoin>

    suspend fun getCoinById(id: Int): Cryptocoin

    suspend fun changeCoinFavoriteState(cryptocoin: Cryptocoin)

    fun getCoinsFlow(): Flow<List<Cryptocoin>>
}