package com.maxkor.core.base.data.di

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ServicesComponentsModule {

    @Provides
    fun provideConnectivityManager(
        @ApplicationContext context: Context,
    ): ConnectivityManager = context.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager

    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context,
    ): NotificationManager = context.getSystemService(
        Service.NOTIFICATION_SERVICE
    ) as NotificationManager
}

