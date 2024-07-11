package com.maxkor.feature.favorites.impl.presentation.di

import com.maxkor.feature.favorites.api.presentation.navigation.FavoritesNavigation
import com.maxkor.feature.favorites.impl.presentation.navigation.FavoritesNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
interface NavigationModule {

    @ActivityRetainedScoped
    @Binds
    fun bindFavoritesNavigation(
        impl: FavoritesNavigationImpl,
    ): FavoritesNavigation
}