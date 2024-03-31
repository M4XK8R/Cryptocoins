package com.maxkor.feature.favorites.impl.domain.di

import com.maxkor.feature.favorites.api.interactor.FavoritesNavigationInteractor
import com.maxkor.feature.favorites.impl.domain.interactor.impl.FavoritesNavigationInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface InteractorModule {

    @Binds
    fun bindFavoritesNavigationInteractor(
        impl: FavoritesNavigationInteractorImpl,
    ): FavoritesNavigationInteractor
}