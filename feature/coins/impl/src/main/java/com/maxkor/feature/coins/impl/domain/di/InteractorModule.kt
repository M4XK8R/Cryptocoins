package com.maxkor.feature.coins.impl.domain.di

import com.maxkor.feature.coins.api.domain.interactor.CoinsDetailInteractor
import com.maxkor.feature.coins.api.domain.interactor.CoinsFavoritesInteractor
import com.maxkor.feature.coins.impl.domain.interactor.CoinsInteractor
import com.maxkor.feature.coins.impl.domain.interactor.impl.CoinsDetailInteractorImpl
import com.maxkor.feature.coins.impl.domain.interactor.impl.CoinsFavoritesInteractorImpl
import com.maxkor.feature.coins.impl.domain.interactor.impl.CoinsInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface CoinsInteractorModule {
    @ViewModelScoped
    @Binds
    fun bindCoinsInteractor(
        impl: CoinsInteractorImpl,
    ): CoinsInteractor
}

@Module
@InstallIn(ViewModelComponent::class)
interface CoinsDetailInteractorModule {
    @ViewModelScoped
    @Binds
    fun bindCoinsDetailInteractor(
        impl: CoinsDetailInteractorImpl,
    ): CoinsDetailInteractor
}

@Module
@InstallIn(ViewModelComponent::class)
interface CoinsFavoritesInteractorModule {
    @ViewModelScoped
    @Binds
    fun bindCoinsFavoritesInteractor(
        impl: CoinsFavoritesInteractorImpl,
    ): CoinsFavoritesInteractor
}