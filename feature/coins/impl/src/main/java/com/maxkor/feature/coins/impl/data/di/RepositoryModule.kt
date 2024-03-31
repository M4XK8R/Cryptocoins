package com.maxkor.feature.coins.impl.data.di

import com.maxkor.feature.coins.impl.data.repository.CoinsRepositoryImpl
import com.maxkor.feature.coins.impl.domain.repository.CoinsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideCoinsRepository(
        impl: CoinsRepositoryImpl,
    ): CoinsRepository
}