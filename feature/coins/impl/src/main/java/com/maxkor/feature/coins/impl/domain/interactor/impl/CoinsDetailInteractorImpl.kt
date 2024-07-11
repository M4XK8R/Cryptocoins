package com.maxkor.feature.coins.impl.domain.interactor.impl

import com.maxkor.core.base.domain.model.Cryptocoin
import com.maxkor.core.base.domain.usecase.UseCase
import com.maxkor.feature.coins.api.domain.interactor.CoinsDetailInteractor
import com.maxkor.feature.coins.impl.domain.mapper.toCryptocoin
import com.maxkor.feature.coins.impl.domain.model.parameters.GetCoinByNameFlowParams
import com.maxkor.feature.coins.impl.domain.usecase.GetCoinByNameFlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoinsDetailInteractorImpl @Inject constructor(
    private val getCoinByNameFlowUseCase: GetCoinByNameFlowUseCase,
) : CoinsDetailInteractor {

    override fun getCoinByNameFlow(name: String): Flow<Cryptocoin> =
        when (
            val result = getCoinByNameFlowUseCase.invoke(
                GetCoinByNameFlowParams(name)
            )
        ) {
            is UseCase.Result.Success -> result.value.map { it.toCryptocoin() }
            is UseCase.Result.Failure -> flowOf(Cryptocoin.Empty)
        }
}