package com.maxkor.feature.detail.impl.data.di.modules

import com.maxkor.feature.detail.impl.data.repository.ImageRepositoryImpl
import com.maxkor.feature.detail.impl.data.repository.ReminderRepositoryImpl
import com.maxkor.feature.detail.impl.domain.repository.ImageRepository
import com.maxkor.feature.detail.impl.domain.repository.ReminderRepository
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
        impl: ReminderRepositoryImpl,
    ): ReminderRepository
}