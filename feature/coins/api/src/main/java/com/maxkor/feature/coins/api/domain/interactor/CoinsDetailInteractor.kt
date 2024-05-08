package com.maxkor.feature.coins.api.domain.interactor

import com.maxkor.feature.coins.api.domain.model.DetailCoin
import kotlinx.coroutines.flow.Flow

interface CoinsDetailInteractor {

    suspend fun getDetailCoinById(id: Int): DetailCoin

    suspend fun getCoinByName(name: String): DetailCoin

    fun getDetailCoinByIdFlow(id: Int): Flow<DetailCoin>
}