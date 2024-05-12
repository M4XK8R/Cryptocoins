package com.maxkor.feature.favorites.impl.domain.di

import com.maxkor.feature.favorites.api.interactor.FavoritesNavigationInteractor
import com.maxkor.feature.favorites.impl.domain.interactor.impl.FavoritesNavigationInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
interface FavoritesNavigationInteractorModule {
    @ActivityRetainedScoped
    @Binds
    fun bindFavoritesNavigationInteractor(
        impl: FavoritesNavigationInteractorImpl,
    ): FavoritesNavigationInteractor
}