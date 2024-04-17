package com.maxkor.feature.detail.impl.data.di.modules

import android.app.AlarmManager
import android.app.DownloadManager
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ServicesComponentsModule {

    @Provides
    fun provideAlarmManager(
        @ApplicationContext context: Context,
    ): AlarmManager = context.getSystemService(
        Context.ALARM_SERVICE
    ) as AlarmManager

    @Provides
    fun provideDownloadManager(
        @ApplicationContext context: Context,
    ): DownloadManager = context.getSystemService(
        Context.DOWNLOAD_SERVICE
    ) as DownloadManager
}

