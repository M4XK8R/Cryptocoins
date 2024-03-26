package com.maxkor.feature.coins.impl.data.local.repository

import com.maxkor.feature.coins.impl.data.local.dao.CoinsDao
import com.maxkor.feature.coins.impl.data.local.mapper.toCoin
import com.maxkor.feature.coins.impl.data.local.mapper.toCoinEntity
import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.domain.repository.LocalDataSourceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSourceRepositoryImpl @Inject constructor(
    private val coinsDao: CoinsDao,
) : LocalDataSourceRepository {

    override suspend fun getCoins(): List<Coin> = coinsDao
        .getAll()
        .map {it.toCoin()}

    override suspend fun getCoinById(id: Int): Coin = coinsDao
        .getById(id)
        .toCoin()

    override suspend fun addCoins(coins: List<Coin>) {
        val coinsEntities = coins.map { it.toCoinEntity() }
        coinsDao.insertAll(coinsEntities)
    }

    override suspend fun changeCoinFavoriteState(coin: Coin) {
        val changedCoin = coin.copy(
            isFavorite = !coin.isFavorite
        )
        coinsDao.updateData(changedCoin.toCoinEntity())
    }

    override suspend fun updateDatabaseData(coins: List<Coin>) =
        coins.forEach { coin ->
            coinsDao.updateData(coin.toCoinEntity())
        }

    override fun getCoinsFlow(): Flow<List<Coin>> = coinsDao
        .getAllFlow()
        .map { coinsEntities ->
            coinsEntities.map { it.toCoin() }
        }
}