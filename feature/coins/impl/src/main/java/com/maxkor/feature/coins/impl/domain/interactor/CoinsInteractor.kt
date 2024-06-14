package com.maxkor.feature.coins.impl.domain.interactor

import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.domain.model.parameters.ChangeCoinFavoriteStateParams
import com.maxkor.feature.coins.impl.domain.model.parameters.DownloadAndUpdateCoinsParams
import kotlinx.coroutines.flow.Flow

interface CoinsInteractor {

    fun getCoinsFlow(): Flow<List<Coin>>

    suspend fun downloadAndUpdateCoins(
        downloadAndUpdateCoinsParams: DownloadAndUpdateCoinsParams,
    )

    suspend fun changeCoinFavoriteState(
        changeCoinFavoriteStateParams: ChangeCoinFavoriteStateParams,
    )
}