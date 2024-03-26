package com.maxkor.feature.coins.api.domain.interactor

import com.maxkor.feature.coins.api.domain.model.CoinFav
import kotlinx.coroutines.flow.Flow

interface CoinsFavoritesInteractor {

    fun getFavCoinsFlow(): Flow<List<CoinFav>>

    suspend fun removeFromFav(coinFav: CoinFav)
}