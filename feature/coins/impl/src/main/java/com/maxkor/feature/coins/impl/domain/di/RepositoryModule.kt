package com.maxkor.feature.coins.impl.domain.di

import com.maxkor.feature.coins.impl.data.local.repository.LocalDataSourceRepositoryImpl
import com.maxkor.feature.coins.impl.data.remote.repository.RemoteDataSourceRepositoryImpl
import com.maxkor.feature.coins.impl.domain.repository.LocalDataSourceRepository
import com.maxkor.feature.coins.impl.domain.repository.RemoteDataSourceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindLocalDataSourceRepository(
        impl: LocalDataSourceRepositoryImpl,
    ): LocalDataSourceRepository

    @Binds
    fun bindRemoteDataSourceRepository(
        impl: RemoteDataSourceRepositoryImpl,
    ): RemoteDataSourceRepository
}