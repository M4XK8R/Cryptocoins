package com.maxkor.feature.coins.impl.domain.usecase

import com.maxkor.core.base.domain.dispatchers.IoDispatcher
import com.maxkor.core.base.domain.usecase.SuspendUseCase
import com.maxkor.feature.coins.impl.domain.repository.CoinsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SaveCoinsToDatabaseUseCase @Inject constructor(
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
    private val coinsRepository: CoinsRepository,
) : SuspendUseCase<Nothing?, Unit>(dispatcherIo) {

    override suspend fun execute(parameters: Nothing?) =
        coinsRepository.saveCoinsToDatabase()
}