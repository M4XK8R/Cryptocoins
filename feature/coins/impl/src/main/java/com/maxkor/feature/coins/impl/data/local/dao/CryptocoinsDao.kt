package com.maxkor.feature.coins.impl.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.maxkor.feature.coins.impl.data.local.model.CryptocoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface CryptocoinsDao {
    @Query("SELECT * FROM  cryptocoins")
    suspend fun getAll(): List<CryptocoinEntity>

    @Query("SELECT * FROM  cryptocoins WHERE id = :id")
    suspend fun getById(id: Int): CryptocoinEntity

    @Upsert
    suspend fun updateData(vararg coins: CryptocoinEntity)

    @Query("SELECT * FROM  cryptocoins")
    fun getAllFlow(): Flow<List<CryptocoinEntity>>
}