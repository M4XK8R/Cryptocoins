package com.maxkor.feature.coins.impl.domain.usecase

import com.maxkor.core.base.domain.usecase.UseCase
import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.domain.model.parameters.GetCoinByNameFlowParams
import com.maxkor.feature.coins.impl.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinByNameFlowUseCase @Inject constructor(
    private val coinsRepository: CoinsRepository,
) : UseCase<GetCoinByNameFlowParams, Flow<Coin>>() {

    override fun execute(parameters: GetCoinByNameFlowParams): Flow<Coin> =
        coinsRepository.getCoinByNameFlow(parameters.coinName)
}

