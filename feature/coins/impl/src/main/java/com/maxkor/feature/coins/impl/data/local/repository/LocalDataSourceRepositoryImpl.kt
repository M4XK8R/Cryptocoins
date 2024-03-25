package com.maxkor.feature.coins.impl.data.local.repository

import com.maxkor.feature.coins.impl.data.local.dao.CryptocoinsDao
import com.maxkor.feature.coins.impl.data.local.mapper.toCryptocoin
import com.maxkor.feature.coins.impl.data.local.mapper.toCryptocoinEntity
import com.maxkor.feature.coins.impl.domain.model.Cryptocoin
import com.maxkor.feature.coins.impl.domain.repository.LocalDataSourceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSourceRepositoryImpl @Inject constructor(
    private val cryptocoinsDao: CryptocoinsDao,
) : LocalDataSourceRepository {

    override suspend fun getCoins(): List<Cryptocoin> = cryptocoinsDao
        .getAll()
        .map { it.toCryptocoin() }

    override suspend fun getCoinById(id: Int): Cryptocoin = cryptocoinsDao
        .getById(id)
        .toCryptocoin()

    override suspend fun addCoins(cryptocoins: List<Cryptocoin>) {
        val cryptocoinEntities = cryptocoins.map { it.toCryptocoinEntity() }
        cryptocoinsDao.insertAll(cryptocoinEntities)
    }

    override suspend fun changeCoinFavoriteState(cryptocoin: Cryptocoin) {
        val changedCryptocoin = cryptocoin.copy(
            isFavorite = !cryptocoin.isFavorite
        )
        cryptocoinsDao.updateData(changedCryptocoin.toCryptocoinEntity())
    }

    override suspend fun updateDatabaseData(cryptocoins: List<Cryptocoin>) =
        cryptocoins.forEach { cryptocoin ->
            cryptocoinsDao.updateData(cryptocoin.toCryptocoinEntity())
        }

    override fun getCoinsFlow(): Flow<List<Cryptocoin>> = cryptocoinsDao
        .getAllFlow()
        .map { cryptocoinsEntities ->
            cryptocoinsEntities.map { it.toCryptocoin() }
        }
}