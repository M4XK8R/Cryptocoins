package com.maxkor.feature.coins.impl.domain.repository

import com.maxkor.feature.coins.impl.domain.model.Coin

interface RemoteDataSourceRepository {

    suspend fun getDataFromServer(): List<Coin>?
}