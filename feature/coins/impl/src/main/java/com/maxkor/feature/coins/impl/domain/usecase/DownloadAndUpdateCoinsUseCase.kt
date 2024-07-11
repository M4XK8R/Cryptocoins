package com.maxkor.feature.coins.impl.domain.usecase

import com.maxkor.core.base.domain.dispatchers.IoDispatcher
import com.maxkor.core.base.domain.repository.CheckerRepository
import com.maxkor.core.base.domain.usecase.SuspendUseCase
import com.maxkor.feature.coins.impl.domain.model.parameters.DownloadAndUpdateCoinsParams
import com.maxkor.feature.coins.impl.domain.repository.CoinsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DownloadAndUpdateCoinsUseCase @Inject constructor(
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
    private val coinsRepository: CoinsRepository,
    private val checkerRepository: CheckerRepository,
) : SuspendUseCase<DownloadAndUpdateCoinsParams, Unit>(dispatcherIo) {

    override suspend fun execute(parameters: DownloadAndUpdateCoinsParams) =
        coinsRepository.downloadAndUpdateCoins(
            hasInternetConnection = checkerRepository.hasInternetConnection(),
            informUserOnFailure = parameters.informUserOnFailure
        )
}