package com.maxkor.feature.coins.impl.domain.interactor.impl

import com.maxkor.core.base.dispatchers.IoDispatcher
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
) : CoinsInteractor {

    override fun getCoinsFlow(): Flow<List<Coin>> =
        coinsRepository.getCoinsFlow()

    override suspend fun changeCoinFavoriteState(coin: Coin) =
        withContext(dispatcherIo) {
            val updatedCoin = coin.copy(
                isFavorite = !coin.isFavorite
            )
            coinsRepository.updateCoin(updatedCoin)
        }

    override suspend fun updateData() =
        withContext(dispatcherIo) {
            val newCoins = coinsRepository.getCoinsFromServer()
            if (newCoins.isNullOrEmpty()) {
                return@withContext
            }
            val currentCoins = coinsRepository.getCoins()
            if (currentCoins.isEmpty()) {
                coinsRepository.updateCoins(newCoins)
            }
            if (currentCoins.isNotEmpty()) {
                val parsedCoins = parseCoins(
                    updatedCoins = newCoins,
                    currentCoins = currentCoins
                )
                coinsRepository.updateCoins(parsedCoins)
            }
        }

    /**
     * Private functions
     */

    private fun parseCoins(
        updatedCoins: List<Coin>,
        currentCoins: List<Coin>,
    ): List<Coin> {
        val parsedCoins = mutableListOf<Coin>()
        updatedCoins.forEach { updatedCoin ->
            val currentCoin = currentCoins.first { currentCoin ->
                currentCoin.id == updatedCoin.id
            }
            val parsedCoin = updatedCoin.copy(
                isFavorite = currentCoin.isFavorite
            )
            parsedCoins.add(parsedCoin)
        }
        return parsedCoins.toList()
    }
}