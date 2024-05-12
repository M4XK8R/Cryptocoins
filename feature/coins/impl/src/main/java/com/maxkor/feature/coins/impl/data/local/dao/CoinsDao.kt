package com.maxkor.feature.coins.impl.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.maxkor.feature.coins.impl.data.local.model.CoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinsDao {

    @Query("SELECT * FROM  cryptocoins")
    suspend fun getAll(): List<CoinEntity>

    @Upsert
    suspend fun update(coinsEntities: List<CoinEntity>)

    @Upsert
    suspend fun update(vararg coinsEntities: CoinEntity)

    @Query("SELECT * FROM  cryptocoins")
    fun getAllFlow(): Flow<List<CoinEntity>>

    @Query("SELECT * FROM  cryptocoins WHERE name = :name")
    fun getByNameFlow(name: String): Flow<CoinEntity>
}