package com.maxkor.feature.coins.impl.domain.interactor

import com.maxkor.core.base.dispatchers.IoDispatcher
import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.domain.repository.LocalDataSourceRepository
import com.maxkor.feature.coins.impl.domain.repository.RemoteDataSourceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoinsInteractorImpl @Inject constructor(
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
    private val localDataSourceRepository: LocalDataSourceRepository,
    private val remoteDataSourceRepository: RemoteDataSourceRepository,
) : CoinsInteractor {

    override fun getCoinsFlow(): Flow<List<Coin>> =
        localDataSourceRepository.getCoinsFlow()

    override suspend fun changeCoinFavoriteState(coin: Coin) =
        withContext(dispatcherIo) {
            localDataSourceRepository.changeCoinFavoriteState(coin)
        }

    override suspend fun updateData() =
        withContext(dispatcherIo) {
            val newData = remoteDataSourceRepository.getDataFromServer()
            val currentData = localDataSourceRepository.getCoins()
            if (currentData.isEmpty()) {
                localDataSourceRepository.addCoins(newData)
            }
            if (currentData.isNotEmpty()) {
                val updatedData: MutableList<Coin> = mutableListOf()
                newData.forEach { coin ->
                    val currentCoin = currentData.first { it.id == coin.id }
                    val updatedCoin = coin.copy(isFavorite = currentCoin.isFavorite)
                    updatedData.add(updatedCoin)
                }
                localDataSourceRepository.updateCoinsData(updatedData)
            }
        }
}