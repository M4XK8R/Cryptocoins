package com.maxkor.feature.coins.impl.domain.usecase

import com.maxkor.core.base.domain.usecase.UseCase
import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinsFlowUseCase @Inject constructor(
    private val coinsRepository: CoinsRepository,
) : UseCase<Nothing?, Flow<List<Coin>>>() {

    override fun execute(parameters: Nothing?): Flow<List<Coin>> =
        coinsRepository.coinsFlow
}

