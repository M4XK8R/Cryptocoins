package com.maxkor.feature.coins.impl.domain.interactor

import com.maxkor.feature.coins.impl.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinsInteractor {

    fun getCoinsFlow(): Flow<List<Coin>>

    suspend fun updateCoins(
        informUserOnFailure: (String) -> Unit,
    )

    suspend fun changeCoinFavoriteState(coin: Coin)
}