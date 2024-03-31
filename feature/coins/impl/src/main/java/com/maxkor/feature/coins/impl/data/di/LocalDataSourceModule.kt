package com.maxkor.feature.coins.impl.data.di

import android.content.Context
import com.maxkor.feature.coins.impl.data.local.dao.CoinsDao
import com.maxkor.feature.coins.impl.data.local.db.CoinsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideCoinsDatabase(
        @ApplicationContext context: Context,
    ): CoinsDatabase = CoinsDatabase.getInstance(
        context = context,
        name = CoinsDatabase.DATABASE_NAME
    )

    @Provides
    @Singleton
    fun provideCoinsDao(
        appDatabase: CoinsDatabase,
    ): CoinsDao = appDatabase.coinsDao()

}