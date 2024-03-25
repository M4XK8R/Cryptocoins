package com.maxkor.feature.coins.impl.domain.interactor

import com.maxkor.core.base.dispatchers.IoDispatcher
import com.maxkor.feature.coins.impl.domain.model.Cryptocoin
import com.maxkor.feature.coins.impl.domain.repository.LocalDataSourceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CryptocoinsInteractorImpl @Inject constructor(
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
    private val localDataSourceRepository: LocalDataSourceRepository,
) : CryptocoinsInteractor {

    override suspend fun getCoins(): List<Cryptocoin> =
        withContext(dispatcherIo) {
            localDataSourceRepository.getCoins()
        }

    override suspend fun getCoinById(id: Int): Cryptocoin =
        withContext(dispatcherIo) {
            localDataSourceRepository.getCoinById(id)
        }

    override suspend fun addCoins(coins: List<Cryptocoin>) =
        withContext(dispatcherIo) {
            localDataSourceRepository.addCoins(coins)
        }

    override suspend fun changeCoinFavoriteState(cryptocoin: Cryptocoin) =
        withContext(dispatcherIo) {
            localDataSourceRepository.changeCoinFavoriteState(cryptocoin)
        }

    override suspend fun updateDatabaseData(cryptocoins: List<Cryptocoin>) =
        withContext(dispatcherIo) {
            localDataSourceRepository.updateDatabaseData(cryptocoins)
        }

    override fun getCoinsFlow(): Flow<List<Cryptocoin>> =
        localDataSourceRepository.getCoinsFlow()
}