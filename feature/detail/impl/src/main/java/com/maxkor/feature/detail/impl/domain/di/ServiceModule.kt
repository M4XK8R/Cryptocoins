package com.maxkor.feature.detail.impl.domain.di

import com.maxkor.feature.detail.api.domain.service.DetailFeatureNotificationService
import com.maxkor.feature.detail.impl.domain.service.DownloadImageService
import com.maxkor.feature.detail.impl.domain.service.ShareImageService
import com.maxkor.feature.detail.impl.domain.service.impl.DetailFeatureNotificationServiceImpl
import com.maxkor.feature.detail.impl.domain.service.impl.DownloadImageServiceImpl
import com.maxkor.feature.detail.impl.domain.service.impl.ShareImageServiceImpl
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
    fun bindDetailFeatureNotificationService(
        impl: DetailFeatureNotificationServiceImpl,
    ): DetailFeatureNotificationService
}