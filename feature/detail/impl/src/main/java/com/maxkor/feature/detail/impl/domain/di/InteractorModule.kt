package com.maxkor.feature.detail.impl.domain.di

import com.maxkor.feature.detail.api.domain.interactor.DetailNavigationInteractor
import com.maxkor.feature.detail.impl.domain.interactor.DetailInteractor
import com.maxkor.feature.detail.impl.domain.interactor.impl.DetailInteractorImpl
import com.maxkor.feature.detail.impl.domain.interactor.impl.DetailNavigationInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface DetailInteractorModule {
    @ViewModelScoped
    @Binds
    fun bindDetailInteractor(
        impl: DetailInteractorImpl,
    ): DetailInteractor
}

@Module
@InstallIn(ActivityRetainedComponent::class)
interface DetailNavigationInteractorModule {
    @ActivityRetainedScoped
    @Binds
    fun bindDetailNavigationInteractor(
        impl: DetailNavigationInteractorImpl,
    ): DetailNavigationInteractor
}