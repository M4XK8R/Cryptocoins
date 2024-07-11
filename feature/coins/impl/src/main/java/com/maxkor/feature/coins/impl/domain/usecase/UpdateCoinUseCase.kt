package com.maxkor.feature.coins.impl.domain.usecase

import com.maxkor.core.base.domain.dispatchers.IoDispatcher
import com.maxkor.core.base.domain.usecase.SuspendUseCase
import com.maxkor.feature.coins.impl.domain.model.parameters.UpdateCoinParams
import com.maxkor.feature.coins.impl.domain.repository.CoinsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UpdateCoinUseCase @Inject constructor(
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
    private val coinsRepository: CoinsRepository,
) : SuspendUseCase<UpdateCoinParams, Unit>(dispatcherIo) {

    override suspend fun execute(parameters: UpdateCoinParams) =
        coinsRepository.updateCoin(parameters.coin)
}