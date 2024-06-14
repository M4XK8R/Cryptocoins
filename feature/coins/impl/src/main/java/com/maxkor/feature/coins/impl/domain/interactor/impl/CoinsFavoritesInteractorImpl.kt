package com.maxkor.feature.coins.impl.domain.interactor.impl

import com.maxkor.core.base.domain.model.Cryptocoin
import com.maxkor.core.base.domain.usecase.UseCase
import com.maxkor.feature.coins.api.domain.interactor.CoinsFavoritesInteractor
import com.maxkor.feature.coins.impl.domain.mapper.toCryptocoin
import com.maxkor.feature.coins.impl.domain.model.parameters.UpdateCoinParams
import com.maxkor.feature.coins.impl.domain.usecase.GetCoinsFlowUseCase
import com.maxkor.feature.coins.impl.domain.usecase.UpdateCoinUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class CoinsFavoritesInteractorImpl @Inject constructor(
    private val getCoinsFlowUseCase: GetCoinsFlowUseCase,
    private val updateCoinUseCase: UpdateCoinUseCase,
) : CoinsFavoritesInteractor {

    override fun getFavoriteCoinsFlow(): Flow<List<Cryptocoin>> =
        when (val result = getCoinsFlowUseCase.invoke(null)) {
            is UseCase.Result.Success -> {
                result.value.transform { coins ->
                    val favoriteCoins = coins
                        .filter { it.isFavorite }
                        .map { it.toCryptocoin() }
                    emit(favoriteCoins)
                }
            }

            is UseCase.Result.Failure -> flowOf(emptyList())
        }

    override suspend fun removeFromFavorites(cryptocoin: Cryptocoin) {
        updateCoinUseCase.invoke(UpdateCoinParams(cryptocoin))
    }
}
