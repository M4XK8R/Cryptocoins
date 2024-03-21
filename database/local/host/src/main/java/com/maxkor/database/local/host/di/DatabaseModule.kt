package com.maxkor.database.local.host.di

import android.content.Context
import com.maxkor.database.local.host.db.CryptocoinsDatabase
import com.maxkor.database.local.intermediarycoins.dao.CryptocoinsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
  internal object DatabaseModule {

    @Singleton
    @Provides
    fun provideCryptocoinsDatabase(
      @ApplicationContext context: Context,
    ): CryptocoinsDatabase = CryptocoinsDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideCryptocoinsDao(
      cryptocoinsDatabase: CryptocoinsDatabase,
    ): CryptocoinsDao = cryptocoinsDatabase.cryptocoinsDao()

}