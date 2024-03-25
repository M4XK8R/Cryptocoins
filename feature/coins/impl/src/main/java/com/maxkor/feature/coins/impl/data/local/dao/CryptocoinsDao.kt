package com.maxkor.feature.coins.impl.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.maxkor.feature.coins.impl.data.local.model.CryptocoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptocoinsDao {
    @Query("SELECT * FROM  cryptocoins")
    suspend fun getAll(): List<CryptocoinEntity>

    @Query("SELECT * FROM  cryptocoins WHERE id = :id")
    suspend fun getById(id: Int): CryptocoinEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(coins: List<CryptocoinEntity>)

    @Upsert
    suspend fun updateData(vararg coins: CryptocoinEntity)

    @Query("SELECT * FROM  cryptocoins")
    fun getAllFlow(): Flow<List<CryptocoinEntity>>
}