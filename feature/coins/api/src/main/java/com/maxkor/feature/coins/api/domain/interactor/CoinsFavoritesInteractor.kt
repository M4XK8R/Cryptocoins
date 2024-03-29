package com.maxkor.feature.coins.api.domain.interactor

import com.maxkor.feature.coins.api.domain.model.FavoriteCoin
import kotlinx.coroutines.flow.Flow

interface CoinsFavoritesInteractor {

    fun getFavoriteCoinsFlow(): Flow<List<FavoriteCoin>>

    suspend fun removeFromFavorites(favoriteCoin: FavoriteCoin)
}