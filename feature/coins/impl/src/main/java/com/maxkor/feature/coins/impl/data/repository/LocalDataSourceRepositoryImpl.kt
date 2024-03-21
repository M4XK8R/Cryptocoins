package com.maxkor.feature.coins.impl.data.repository

import com.maxkor.database.local.intermediarycoins.dao.CryptocoinsDao
import com.maxkor.feature.coins.impl.data.mapper.toCryptocoin
import com.maxkor.feature.coins.impl.domain.model.Cryptocoin
import com.maxkor.feature.coins.impl.domain.repository.LocalDataSourceRepository
import javax.inject.Inject

class LocalDataSourceRepositoryImpl @Inject constructor(
    private val dao: CryptocoinsDao,
) : LocalDataSourceRepository {
    override suspend fun getCoins(): List<Cryptocoin> =
        dao.getAll().map { it.toCryptocoin() }

    override suspend fun getCoinById(id: Int): Cryptocoin {
        TODO("Not yet implemented")
    }
}