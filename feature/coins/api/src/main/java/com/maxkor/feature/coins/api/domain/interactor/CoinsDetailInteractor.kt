package com.maxkor.feature.coins.api.domain.interactor

import com.maxkor.core.base.domain.model.Cryptocoin
import kotlinx.coroutines.flow.Flow

interface CoinsDetailInteractor {

    suspend fun getDetailCoinById(id: Int): Cryptocoin

    suspend fun getCoinByName(name: String): Cryptocoin

    fun getDetailCoinByIdFlow(id: Int): Flow<Cryptocoin>
}