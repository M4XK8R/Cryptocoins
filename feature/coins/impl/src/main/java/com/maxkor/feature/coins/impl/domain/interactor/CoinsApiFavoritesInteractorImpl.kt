package com.maxkor.feature.coins.impl.domain.interactor

import com.maxkor.core.base.dispatchers.IoDispatcher
import com.maxkor.feature.coins.api.domain.interactor.CoinsFavoritesInteractor
import com.maxkor.feature.coins.api.domain.model.CryptocoinFav
import com.maxkor.feature.coins.impl.domain.mapper.toCryptocoin
import com.maxkor.feature.coins.impl.domain.mapper.toCryptocoinFav
import com.maxkor.feature.coins.impl.domain.repository.LocalDataSourceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

internal class CoinsApiFavoritesInteractorImpl @Inject constructor(
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
    private val localDataSourceRepository: LocalDataSourceRepository,
) : CoinsFavoritesInteractor {

    override fun getAllFlow(): Flow<List<CryptocoinFav>> =
        localDataSourceRepository.getCoinsFlow()
            .transform { cryptocoins ->
                cryptocoins.filter { it.isFavorite }
                    .map { it.toCryptocoinFav() }
            }

    override suspend fun remove(cryptocoinFav: CryptocoinFav) {
        localDataSourceRepository.changeCoinFavoriteState(
            cryptocoinFav.toCryptocoin()
        )
    }
}