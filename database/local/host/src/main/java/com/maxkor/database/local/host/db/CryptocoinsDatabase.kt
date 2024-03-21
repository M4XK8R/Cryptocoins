package com.maxkor.database.local.host.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maxkor.database.local.intermediarycoins.dao.CryptocoinsDao
import com.maxkor.database.local.intermediarycoins.entity.CryptocoinEntity

private const val DATABASE_VERSION = 1
private const val DATABASE_NAME = "cryptocoins-db"

@Database(
    entities = [
        CryptocoinEntity::class,
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class CryptocoinsDatabase : RoomDatabase() {

    abstract fun cryptocoinsDao(): CryptocoinsDao

    companion object {

        @Volatile
        private var instance: CryptocoinsDatabase? = null

        fun getInstance(context: Context): CryptocoinsDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): CryptocoinsDatabase {
            return Room.databaseBuilder(
                context,
                CryptocoinsDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}