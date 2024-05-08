package com.maxkor.feature.coins.impl.domain.interactor.impl

import com.maxkor.core.base.domain.dispatchers.IoDispatcher
import com.maxkor.feature.coins.api.domain.interactor.CoinsDetailInteractor
import com.maxkor.feature.coins.api.domain.model.DetailCoin
import com.maxkor.feature.coins.impl.domain.mapper.toDetailCoin
import com.maxkor.feature.coins.impl.domain.repository.CoinsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoinsDetailInteractorImpl @Inject constructor(
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
    private val coinsRepository: CoinsRepository,
) : CoinsDetailInteractor {

    override suspend fun getDetailCoinById(id: Int): DetailCoin =
        withContext(dispatcherIo) {
            val coin = coinsRepository.getCoinById(id)
            coin.toDetailCoin()
        }

    override suspend fun getCoinByName(name: String): DetailCoin =
        withContext(dispatcherIo) {
            val coin = coinsRepository.getCoinByName(name)
            coin.toDetailCoin()
        }

    override fun getDetailCoinByIdFlow(id: Int): Flow<DetailCoin> =
        coinsRepository.getCoinByIdFlow(id)
            .map { it.toDetailCoin() }
}