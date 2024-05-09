package com.maxkor.feature.coins.impl.domain.interactor.impl

import com.maxkor.core.base.domain.dispatchers.IoDispatcher
import com.maxkor.core.base.domain.model.Cryptocoin
import com.maxkor.feature.coins.api.domain.interactor.CoinsDetailInteractor
import com.maxkor.feature.coins.impl.domain.mapper.toCryptocoin
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

    override suspend fun getDetailCoinById(id: Int): Cryptocoin =
        withContext(dispatcherIo) {
            val coin = coinsRepository.getCoinById(id)
            coin.toCryptocoin()
        }

    override suspend fun getCoinByName(name: String): Cryptocoin =
        withContext(dispatcherIo) {
            val coin = coinsRepository.getCoinByName(name)
            coin.toCryptocoin()
        }

    override fun getDetailCoinByIdFlow(id: Int): Flow<Cryptocoin> =
        coinsRepository.getCoinByIdFlow(id)
            .map { it.toCryptocoin() }
}