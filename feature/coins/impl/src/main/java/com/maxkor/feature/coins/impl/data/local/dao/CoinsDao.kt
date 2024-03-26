package com.maxkor.feature.coins.impl.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.maxkor.feature.coins.impl.data.local.model.CoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinsDao {
    @Query("SELECT * FROM  cryptocoins")
    suspend fun getAll(): List<CoinEntity>

    @Query("SELECT * FROM  cryptocoins WHERE id = :id")
    suspend fun getById(id: Int): CoinEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(coinsEntities: List<CoinEntity>)

    @Upsert
    suspend fun updateData(vararg coinsEntities: CoinEntity)

    @Query("SELECT * FROM  cryptocoins")
    fun getAllFlow(): Flow<List<CoinEntity>>
}