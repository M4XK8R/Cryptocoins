package com.maxkor.feature.coins.api.domain.interactor

import com.maxkor.core.base.domain.model.Cryptocoin
import kotlinx.coroutines.flow.Flow

interface CoinsDetailInteractor {

    fun getCoinByNameFlow(name: String): Flow<Cryptocoin>
}