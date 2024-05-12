package com.maxkor.core.base.data.di

import com.maxkor.core.base.data.repository.CheckerRepositoryImpl
import com.maxkor.core.base.domain.repository.CheckerRepository
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
    fun bindCheckerRepository(
        impl: CheckerRepositoryImpl,
    ): CheckerRepository
}