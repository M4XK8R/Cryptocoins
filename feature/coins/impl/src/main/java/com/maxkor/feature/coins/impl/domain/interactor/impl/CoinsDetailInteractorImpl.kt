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

    override fun getCoinByNameFlow(name: String): Flow<Cryptocoin> =
        coinsRepository.getCoinByNameFlow(name)
            .map { it.toCryptocoin() }
}