package com.maxkor.feature.coins.api.domain.interactor

import com.maxkor.feature.coins.api.domain.model.CryptocoinFav
import kotlinx.coroutines.flow.Flow

interface CoinsFavoritesInteractor {

    fun getAllFlow(): Flow<List<CryptocoinFav>>

    suspend fun remove(cryptocoinFav: CryptocoinFav)
}