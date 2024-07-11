package com.maxkor.feature.coins.impl.presentation.di

import com.maxkor.feature.coins.api.presentation.navigation.CoinsNavigation
import com.maxkor.feature.coins.impl.presentation.navigation.CoinsNavigationImpl
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
    fun bindCoinsNavigation(
        impl: CoinsNavigationImpl,
    ): CoinsNavigation
}
