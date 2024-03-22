package com.maxkor.feature.coins.impl.domain.di

import com.maxkor.feature.coins.impl.data.local.repository.LocalDataSourceRepositoryImpl
import com.maxkor.feature.coins.impl.domain.repository.LocalDataSourceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindLocalDataSourceRepository(
        impl: LocalDataSourceRepositoryImpl,
    ): LocalDataSourceRepository
}