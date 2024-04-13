package com.maxkor.core.base.data.di

import com.maxkor.core.base.data.service.InternetCheckerServiceImpl
import com.maxkor.core.base.data.service.NotificationServiceImpl
import com.maxkor.core.base.data.service.PermissionsCheckerServiceImpl
import com.maxkor.core.base.domain.service.InternetCheckerService
import com.maxkor.core.base.domain.service.NotificationService
import com.maxkor.core.base.domain.service.PermissionsCheckerService
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

    @Binds
    fun bindPermissionsCheckerService(
        impl: PermissionsCheckerServiceImpl,
    ): PermissionsCheckerService

    @Binds
    fun bindInternetCheckerService(
        impl: InternetCheckerServiceImpl,
    ): InternetCheckerService
}