package com.maxkor.feature.coins.impl.domain.interactor

import com.maxkor.core.base.dispatchers.IoDispatcher
import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.domain.repository.LocalDataSourceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoinsInteractorImpl @Inject constructor(
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
    private val localDataSourceRepository: LocalDataSourceRepository,
) : CoinsInteractor {

    override suspend fun getCoins(): List<Coin> =
        withContext(dispatcherIo) {
            localDataSourceRepository.getCoins()
        }

    override suspend fun getCoinById(id: Int): Coin =
        withContext(dispatcherIo) {
            localDataSourceRepository.getCoinById(id)
        }

    override suspend fun addCoins(coins: List<Coin>) =
        withContext(dispatcherIo) {
            localDataSourceRepository.addCoins(coins)
        }

    override suspend fun changeCoinFavoriteState(coin: Coin) =
        withContext(dispatcherIo) {
            localDataSourceRepository.changeCoinFavoriteState(coin)
        }

    override suspend fun updateDatabaseData(coins: List<Coin>) =
        withContext(dispatcherIo) {
            localDataSourceRepository.updateDatabaseData(coins)
        }

    override fun getCoinsFlow(): Flow<List<Coin>> =
        localDataSourceRepository.getCoinsFlow()
}