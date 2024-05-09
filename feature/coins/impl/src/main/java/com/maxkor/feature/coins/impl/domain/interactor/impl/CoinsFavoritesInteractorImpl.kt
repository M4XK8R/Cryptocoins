package com.maxkor.feature.coins.impl.domain.interactor.impl

import com.maxkor.core.base.domain.dispatchers.IoDispatcher
import com.maxkor.core.base.domain.model.Cryptocoin
import com.maxkor.feature.coins.api.domain.interactor.CoinsFavoritesInteractor
import com.maxkor.feature.coins.impl.domain.mapper.toCoin
import com.maxkor.feature.coins.impl.domain.mapper.toCryptocoin
import com.maxkor.feature.coins.impl.domain.repository.CoinsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoinsFavoritesInteractorImpl @Inject constructor(
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
    private val coinsRepository: CoinsRepository,
) : CoinsFavoritesInteractor {

    override fun getFavoriteCoinsFlow(): Flow<List<Cryptocoin>> =
        coinsRepository.getCoinsFlow()
            .transform { coins ->
                val favoriteCoins = coins
                    .filter { it.isFavorite }
                    .map { it.toCryptocoin() }
                emit(favoriteCoins)
            }

    override suspend fun removeFromFavorites(cryptocoin: Cryptocoin) =
        withContext(dispatcherIo) {
            coinsRepository.updateCoin(cryptocoin.toCoin())
        }
}
