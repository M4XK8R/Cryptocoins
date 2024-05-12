package com.maxkor.feature.coins.impl.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maxkor.feature.coins.impl.data.local.dao.CoinsDao
import com.maxkor.feature.coins.impl.data.local.model.CoinEntity

@Database(
    entities = [CoinEntity::class],
    version = CoinsDatabase.DATABASE_VERSION,
    exportSchema = false
)
abstract class CoinsDatabase : RoomDatabase() {

    abstract fun coinsDao(): CoinsDao

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "cryptocoins-db"

        @Volatile
        private var instance: CoinsDatabase? = null

        fun getInstance(
            context: Context,
            name: String,
        ): CoinsDatabase = instance ?: synchronized(this) {
            instance ?: buildDatabase(
                context = context,
                name = name
            ).also { instance = it }
        }

        private fun buildDatabase(
            context: Context,
            name: String,
        ): CoinsDatabase = Room.databaseBuilder(
            context = context,
            klass = CoinsDatabase::class.java,
            name = name
        ).build()
    }
}