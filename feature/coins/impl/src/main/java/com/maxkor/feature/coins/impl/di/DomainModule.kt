package com.maxkor.feature.coins.impl.di

import com.maxkor.feature.coins.impl.data.repository.LocalDataSourceRepositoryImpl
import com.maxkor.feature.coins.impl.domain.repository.LocalDataSourceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun bindLocalDataSourceRepository(
        impl: LocalDataSourceRepositoryImpl,
    ): LocalDataSourceRepository

//    @Provides
//    fun provideInteractor(
//
//    ) : CryptocoinInteractor{
//        return CryptocoinInteractor()
//    }
}