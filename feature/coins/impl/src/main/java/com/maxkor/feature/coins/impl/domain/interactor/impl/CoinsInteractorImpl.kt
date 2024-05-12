package com.maxkor.feature.coins.impl.domain.interactor.impl

import android.content.Context
import com.maxkor.core.base.domain.dispatchers.IoDispatcher
import com.maxkor.core.base.domain.repository.CheckerRepository
import com.maxkor.feature.coins.impl.domain.interactor.CoinsInteractor
import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.domain.repository.CoinsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoinsInteractorImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
    private val coinsRepository: CoinsRepository,
    private val checkerRepository: CheckerRepository,
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

    override suspend fun updateData(
        informUserOnFailure: (String) -> Unit,
    ): Unit =
        if (checkerRepository.hasInternetConnection()) {
            withContext(dispatcherIo) {
                val newCoins = coinsRepository.getCoinsFromServer(
                    informUserOnFailure = informUserOnFailure
                )
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
        } else {
            informUserOnFailure(
                context.getString(
                    com.maxkor.core.base.R.string.no_internet_connection_warning
                )
            )
        }

    /**
     * Private sector
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