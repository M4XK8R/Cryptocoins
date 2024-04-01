package com.maxkor.cryptocoins.di

import com.maxkor.cryptocoins.service.impl.DetailFeatureAlarmServiceImpl
import com.maxkor.feature.detail.api.domain.service.DetailFeatureAlarmService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DetailFeatureModule {

    @Binds
    fun bindDetailFeatureAlarmService(
        impl: DetailFeatureAlarmServiceImpl,
    ): DetailFeatureAlarmService
}