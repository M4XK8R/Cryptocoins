package com.maxkor.feature.coins.impl.domain.interactor.api

import com.maxkor.core.base.dispatchers.IoDispatcher
import com.maxkor.feature.coins.api.domain.interactor.CoinsFavoritesInteractor
import com.maxkor.feature.coins.api.domain.model.CoinFav
import com.maxkor.feature.coins.impl.domain.mapper.toCoin
import com.maxkor.feature.coins.impl.domain.mapper.toCoinFav
import com.maxkor.feature.coins.impl.domain.repository.LocalDataSourceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class FavoritesInteractorImpl @Inject constructor(
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
    private val localDataSourceRepository: LocalDataSourceRepository,
) : CoinsFavoritesInteractor {

    override fun getFavCoinsFlow(): Flow<List<CoinFav>> = localDataSourceRepository
        .getCoinsFlow()
        .transform { cryptocoins ->
            cryptocoins.filter { it.isFavorite }
                .map { it.toCoinFav() }
        }

    override suspend fun removeFromFav(coinFav: CoinFav) = localDataSourceRepository
        .changeCoinFavoriteState(coinFav.toCoin())
}
