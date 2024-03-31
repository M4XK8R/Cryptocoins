package com.maxkor.feature.detail.impl.domain.di

import com.maxkor.feature.detail.api.domain.interactor.DetailNavigationInteractor
import com.maxkor.feature.detail.impl.domain.interactor.DetailInteractor
import com.maxkor.feature.detail.impl.domain.interactor.impl.DetailInteractorImpl
import com.maxkor.feature.detail.impl.domain.interactor.impl.DetailNavigationInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface InteractorModule {

    @Binds
    fun bindDetailNavigationInteractor(
        impl: DetailNavigationInteractorImpl,
    ): DetailNavigationInteractor

    @Binds
    fun bindDetailInteractor(
        impl: DetailInteractorImpl,
    ): DetailInteractor
}