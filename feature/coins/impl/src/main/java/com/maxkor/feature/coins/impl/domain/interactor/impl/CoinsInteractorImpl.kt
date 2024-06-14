package com.maxkor.feature.coins.impl.domain.interactor.impl

import com.maxkor.core.base.domain.usecase.UseCase
import com.maxkor.feature.coins.impl.domain.interactor.CoinsInteractor
import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.domain.model.parameters.ChangeCoinFavoriteStateParams
import com.maxkor.feature.coins.impl.domain.model.parameters.DownloadAndUpdateCoinsParams
import com.maxkor.feature.coins.impl.domain.usecase.ChangeCoinFavoriteStateUseCase
import com.maxkor.feature.coins.impl.domain.usecase.DownloadAndUpdateCoinsUseCase
import com.maxkor.feature.coins.impl.domain.usecase.GetCoinsFlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CoinsInteractorImpl @Inject constructor(
    private val getCoinsFlowUseCase: GetCoinsFlowUseCase,
    private val downloadAndUpdateCoinsUseCase: DownloadAndUpdateCoinsUseCase,
    private val changeCoinFavoriteStateUseCase: ChangeCoinFavoriteStateUseCase,
) : CoinsInteractor {

    override fun getCoinsFlow(): Flow<List<Coin>> =
        when (val result = getCoinsFlowUseCase.invoke(null)) {
            is UseCase.Result.Success -> result.value
            is UseCase.Result.Failure -> flowOf(emptyList())
        }

    override suspend fun downloadAndUpdateCoins(
        downloadAndUpdateCoinsParams: DownloadAndUpdateCoinsParams,
    ) {
        downloadAndUpdateCoinsUseCase.invoke(downloadAndUpdateCoinsParams)
    }

    override suspend fun changeCoinFavoriteState(
        changeCoinFavoriteStateParams: ChangeCoinFavoriteStateParams,
    ) {
        val updatedParams = ChangeCoinFavoriteStateParams(
            coin = changeCoinFavoriteStateParams.coin.copy(
                isFavorite = !changeCoinFavoriteStateParams.coin.isFavorite
            )
        )
        changeCoinFavoriteStateUseCase.invoke(updatedParams)
    }
}
