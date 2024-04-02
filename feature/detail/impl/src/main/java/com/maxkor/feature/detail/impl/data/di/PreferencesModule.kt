package com.maxkor.feature.detail.impl.data.di

import com.maxkor.feature.detail.impl.data.preferences.DetailPreferencesImpl
import com.maxkor.feature.detail.impl.domain.preferences.DetailPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface PreferencesModule {

    @Binds
    @Singleton
    fun bindDetailPreferences(
        impl: DetailPreferencesImpl,
    ): DetailPreferences
}