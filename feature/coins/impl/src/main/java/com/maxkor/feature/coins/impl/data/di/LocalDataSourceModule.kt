package com.maxkor.feature.coins.impl.data.di

import android.content.Context
import com.maxkor.feature.coins.impl.data.local.dao.CryptocoinsDao
import com.maxkor.feature.coins.impl.data.local.db.CryptocoinsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideCryptocoinsDatabase(
        @ApplicationContext context: Context,
    ): CryptocoinsDatabase = CryptocoinsDatabase.getInstance(context)

    @Provides
    fun provideCryptocoinsDao(
        appDatabase: CryptocoinsDatabase,
    ): CryptocoinsDao = appDatabase.cryptocoinsDao()

}