package com.maxkor.feature.coins.api.domain.interactor

import com.maxkor.core.base.domain.model.Cryptocoin
import kotlinx.coroutines.flow.Flow

interface CoinsFavoritesInteractor {

    fun getFavoriteCoinsFlow(): Flow<List<Cryptocoin>>

    suspend fun removeFromFavorites(cryptocoin: Cryptocoin)
}