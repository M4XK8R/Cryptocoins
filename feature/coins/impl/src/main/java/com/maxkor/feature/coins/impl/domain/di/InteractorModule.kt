package com.maxkor.feature.coins.impl.domain.di

import com.maxkor.feature.coins.api.domain.interactor.CoinsFavoritesInteractor
import com.maxkor.feature.coins.api.domain.interactor.CoinsNavigationInteractor
import com.maxkor.feature.coins.impl.domain.interactor.CoinsInteractor
import com.maxkor.feature.coins.impl.domain.interactor.CoinsInteractorImpl
import com.maxkor.feature.coins.impl.domain.interactor.api.CoinsFavoritesInteractorImpl
import com.maxkor.feature.coins.impl.domain.interactor.api.CoinsNavigationInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface InteractorModule {

    @Binds
    fun bindCoinsInteractor(
        impl: CoinsInteractorImpl,
    ): CoinsInteractor

    @Binds
    fun bindCoinsNavigationInteractor(
        impl: CoinsNavigationInteractorImpl,
    ): CoinsNavigationInteractor

    @Binds
    fun bindCoinsFavoritesInteractor(
        impl: CoinsFavoritesInteractorImpl,
    ): CoinsFavoritesInteractor

}