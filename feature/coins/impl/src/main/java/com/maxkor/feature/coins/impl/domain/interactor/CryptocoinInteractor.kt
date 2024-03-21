package com.maxkor.feature.coins.impl.domain.interactor

import com.maxkor.core.base.dispatchers.IoDispatcher
import com.maxkor.feature.coins.impl.domain.model.Cryptocoin
import com.maxkor.feature.coins.impl.domain.repository.LocalDataSourceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CryptocoinInteractor @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository,
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
) {
    suspend fun getCoins(): List<Cryptocoin> =
        withContext(dispatcherIo) {
            localDataSourceRepository.getCoins()
        }

//    suspend fun getCoinById(id: Int): Cryptocoin {
//
//    }
}