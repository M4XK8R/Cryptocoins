package com.maxkor.feature.coins.impl.domain.interactor

import com.maxkor.feature.coins.impl.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinsInteractor {

    fun informIfInternetIsNotAvailable(): String?

    fun getCoinsFlow(): Flow<List<Coin>>

    suspend fun changeCoinFavoriteState(coin: Coin)

    suspend fun updateData(
        informUserOnFailure: (String) -> Unit,
    )
}