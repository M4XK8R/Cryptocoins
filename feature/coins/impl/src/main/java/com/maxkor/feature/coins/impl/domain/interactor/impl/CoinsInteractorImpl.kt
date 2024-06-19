package com.maxkor.feature.coins.impl.domain.interactor.impl

import com.maxkor.core.base.domain.usecase.UseCase
import com.maxkor.feature.coins.impl.domain.interactor.CoinsInteractor
import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.domain.model.parameters.DownloadAndUpdateCoinsParams
import com.maxkor.feature.coins.impl.domain.model.parameters.UpdateCoinParams
import com.maxkor.feature.coins.impl.domain.usecase.DownloadAndUpdateCoinsUseCase
import com.maxkor.feature.coins.impl.domain.usecase.GetCoinsFlowUseCase
import com.maxkor.feature.coins.impl.domain.usecase.SaveCoinsToDatabaseUseCase
import com.maxkor.feature.coins.impl.domain.usecase.UpdateCoinUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CoinsInteractorImpl @Inject constructor(
    private val getCoinsFlowUseCase: GetCoinsFlowUseCase,
    private val downloadAndUpdateCoinsUseCase: DownloadAndUpdateCoinsUseCase,
    private val updateCoinUseCase: UpdateCoinUseCase,
    private val saveCoinsToDatabaseUseCase: SaveCoinsToDatabaseUseCase,
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
        updateCoinParams: UpdateCoinParams,
    ) {
        val updatedCoin = updateCoinParams.coin.copy(
            isFavorite = !updateCoinParams.coin.isFavorite
        )
        updateCoinUseCase.invoke(UpdateCoinParams(updatedCoin))
    }

    override suspend fun saveCoinsToDatabase() {
        saveCoinsToDatabaseUseCase.invoke(null)
    }
}