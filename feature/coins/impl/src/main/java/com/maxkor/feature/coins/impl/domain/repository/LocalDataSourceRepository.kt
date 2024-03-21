package com.maxkor.feature.coins.impl.domain.repository

import com.maxkor.feature.coins.impl.domain.model.Cryptocoin

interface LocalDataSourceRepository {

    suspend fun getCoins(): List<Cryptocoin>

    suspend fun getCoinById(id: Int): Cryptocoin

}