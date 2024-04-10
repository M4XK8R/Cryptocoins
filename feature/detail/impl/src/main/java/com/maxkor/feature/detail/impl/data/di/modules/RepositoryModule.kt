package com.maxkor.feature.detail.impl.data.di.modules

import com.maxkor.feature.detail.impl.data.repository.ImageRepositoryImpl
import com.maxkor.feature.detail.impl.data.repository.RemainderRepositoryImpl
import com.maxkor.feature.detail.impl.domain.repository.ImageRepository
import com.maxkor.feature.detail.impl.domain.repository.RemainderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindImageRepository(
        impl: ImageRepositoryImpl,
    ): ImageRepository

    @Binds
    fun bindRemainderRepository(
        impl: RemainderRepositoryImpl,
    ): RemainderRepository
}