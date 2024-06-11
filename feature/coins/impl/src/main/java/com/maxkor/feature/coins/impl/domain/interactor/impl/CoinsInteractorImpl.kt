package com.maxkor.feature.coins.impl.domain.interactor.impl

import com.maxkor.core.base.domain.dispatchers.IoDispatcher
import com.maxkor.core.base.domain.repository.CheckerRepository
import com.maxkor.feature.coins.impl.domain.interactor.CoinsInteractor
import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.domain.repository.CoinsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoinsInteractorImpl @Inject constructor(
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
    private val coinsRepository: CoinsRepository,
    private val checkerRepository: CheckerRepository,
) : CoinsInteractor {

    override fun getCoinsFlow(): Flow<List<Coin>> =
        coinsRepository.getCoinsFlow()

    override suspend fun updateCoins(
        informUserOnFailure: (String) -> Unit,
    ) = withContext(dispatcherIo) {
        coinsRepository.updateCoins(
            hasInternetConnection = checkerRepository.hasInternetConnection(),
            informUserOnFailure = informUserOnFailure
        )
    }

    override suspend fun changeCoinFavoriteState(coin: Coin) =
        withContext(dispatcherIo) {
            val updatedCoin = coin.copy(
                isFavorite = !coin.isFavorite
            )
            coinsRepository.updateCoin(updatedCoin)
        }
}
