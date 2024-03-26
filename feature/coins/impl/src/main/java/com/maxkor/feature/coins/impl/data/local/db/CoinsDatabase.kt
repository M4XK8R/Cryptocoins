package com.maxkor.feature.coins.impl.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maxkor.feature.coins.impl.data.local.dao.CoinsDao
import com.maxkor.feature.coins.impl.data.local.model.CoinEntity

private const val DATABASE_VERSION = 1
private const val DATABASE_NAME = "cryptocoins-db"

@Database(
    entities = [CoinEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class CoinsDatabase : RoomDatabase() {

    abstract fun coinsDao(): CoinsDao

    companion object {
        @Volatile
        private var instance: CoinsDatabase? = null

        fun getInstance(context: Context): CoinsDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context): CoinsDatabase = Room
            .databaseBuilder(
                context,
                CoinsDatabase::class.java,
                DATABASE_NAME
            ).build()

    }
}