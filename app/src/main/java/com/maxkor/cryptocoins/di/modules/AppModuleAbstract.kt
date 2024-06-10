package com.maxkor.cryptocoins.di.modules

import com.maxkor.cryptocoins.broadcast.DetailFeatureReceiverImpl
import com.maxkor.feature.detail.api.presentation.broadcast.DetailFeatureReceiver
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModuleAbstract {

    @Binds
    @Singleton
    fun bindDetailFeatureReceiver(
        impl: DetailFeatureReceiverImpl,
    ): DetailFeatureReceiver
}
