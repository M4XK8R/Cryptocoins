package com.maxkor.feature.coins.impl.domain.interactor.api

import com.maxkor.core.base.dispatchers.IoDispatcher
import com.maxkor.feature.coins.api.domain.interactor.CoinsFavoritesInteractor
import com.maxkor.feature.coins.api.domain.model.FavoriteCoin
import com.maxkor.feature.coins.impl.domain.mapper.toCoin
import com.maxkor.feature.coins.impl.domain.mapper.toCoinFav
import com.maxkor.feature.coins.impl.domain.repository.LocalDataSourceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoinsFavoritesInteractorImpl @Inject constructor(
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
    private val localDataSourceRepository: LocalDataSourceRepository,
) : CoinsFavoritesInteractor {

    override fun getFavoriteCoinsFlow(): Flow<List<FavoriteCoin>> = localDataSourceRepository
        .getCoinsFlow()
        .transform { coins ->
            val coinsFavs = coins
                .filter { it.isFavorite }
                .map { it.toCoinFav() }
            emit(coinsFavs)
        }

    override suspend fun removeFromFavorites(favoriteCoin: FavoriteCoin) =
        withContext(dispatcherIo) {
            localDataSourceRepository.changeCoinFavoriteState(favoriteCoin.toCoin())
        }

}
