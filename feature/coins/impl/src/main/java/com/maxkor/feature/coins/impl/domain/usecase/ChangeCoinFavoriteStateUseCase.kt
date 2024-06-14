package com.maxkor.feature.coins.impl.domain.usecase

import com.maxkor.core.base.domain.dispatchers.IoDispatcher
import com.maxkor.core.base.domain.usecase.SuspendUseCase
import com.maxkor.feature.coins.impl.domain.model.parameters.ChangeCoinFavoriteStateParams
import com.maxkor.feature.coins.impl.domain.repository.CoinsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ChangeCoinFavoriteStateUseCase @Inject constructor(
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
    private val coinsRepository: CoinsRepository,
) : SuspendUseCase<ChangeCoinFavoriteStateParams, Unit>(dispatcherIo) {

    override suspend fun execute(parameters: ChangeCoinFavoriteStateParams) =
        coinsRepository.updateCoin(parameters.coin)
}