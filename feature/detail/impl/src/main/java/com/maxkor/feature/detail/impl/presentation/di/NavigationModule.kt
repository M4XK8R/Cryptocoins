package com.maxkor.feature.detail.impl.presentation.di

import com.maxkor.feature.detail.api.presentation.navigation.DetailNavigation
import com.maxkor.feature.detail.impl.presentation.navigation.DetailNavigationImpl
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
    fun bindDetailNavigation(
        impl: DetailNavigationImpl,
    ): DetailNavigation
}