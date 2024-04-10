package com.maxkor.feature.detail.impl.data.di.modules

import com.maxkor.core.base.data.service.NotificationServiceImpl
import com.maxkor.feature.detail.impl.domain.service.AlarmService
import com.maxkor.feature.detail.impl.domain.service.DownloadImageService
import com.maxkor.core.base.domain.service.NotificationService
import com.maxkor.feature.detail.impl.data.di.qualifiers.DetailFeatureImplementation
import com.maxkor.feature.detail.impl.data.service.AlarmServiceImpl
import com.maxkor.feature.detail.impl.domain.service.ShareImageService
import com.maxkor.feature.detail.impl.data.service.DownloadImageServiceImpl
import com.maxkor.feature.detail.impl.data.service.ShareImageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ServiceModule {

    @Binds
    fun bindDownloadImageService(
        impl: DownloadImageServiceImpl,
    ): DownloadImageService

    @Binds
    fun bindShareImageService(
        impl: ShareImageServiceImpl,
    ): ShareImageService

    @Binds
    @DetailFeatureImplementation
    fun bindNotificationService(
        impl: NotificationServiceImpl,
    ): NotificationService

    @Binds
    fun bindAlarmService(
        impl: AlarmServiceImpl,
    ): AlarmService
}

