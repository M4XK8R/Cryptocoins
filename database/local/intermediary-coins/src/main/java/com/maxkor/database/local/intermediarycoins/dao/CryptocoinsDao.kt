package com.maxkor.database.local.intermediarycoins.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.maxkor.database.local.intermediarycoins.entity.CryptocoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptocoinsDao {

    @Query("SELECT * FROM cryptocoins")
    suspend fun getAll(): List<CryptocoinEntity>

    @Query("SELECT * FROM cryptocoins where id= :id")
    suspend fun getCoinById(id: Int): CryptocoinEntity

    @Upsert
    suspend fun updateDbData(coins: List<CryptocoinEntity>)

    @Query("SELECT * FROM cryptocoins")
    fun getCoinsFlow(): Flow<List<CryptocoinEntity>>
}