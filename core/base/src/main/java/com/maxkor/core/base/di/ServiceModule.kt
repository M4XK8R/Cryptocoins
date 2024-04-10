package com.maxkor.core.base.di

import com.maxkor.core.base.data.service.NotificationServiceImpl
import com.maxkor.core.base.domain.service.NotificationService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ServiceModule {

    @Binds
    fun bindNotificationService(
        impl: NotificationServiceImpl,
    ): NotificationService
}