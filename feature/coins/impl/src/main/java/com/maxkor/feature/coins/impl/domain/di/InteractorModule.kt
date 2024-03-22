package com.maxkor.feature.coins.impl.domain.di

import com.maxkor.feature.coins.api.domain.interactor.CoinsFavoritesInteractor
import com.maxkor.feature.coins.api.domain.interactor.CoinsNavigationInteractor
import com.maxkor.feature.coins.impl.domain.interactor.CoinsApiFavoritesInteractorImpl
import com.maxkor.feature.coins.impl.domain.interactor.CoinsApiNavigationInteractorImpl
import com.maxkor.feature.coins.impl.domain.interactor.CryptocoinsInteractor
import com.maxkor.feature.coins.impl.domain.interactor.CryptocoinsInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface InteractorModule {

    @Binds
    fun bindCryptocoinsInteractor(
        impl: CryptocoinsInteractorImpl,
    ): CryptocoinsInteractor

    @Binds
    fun bindCoinsNavigationInteractor(
        impl: CoinsApiNavigationInteractorImpl,
    ): CoinsNavigationInteractor

    @Binds
    fun bindCoinsFavoritesInteractor(
        impl: CoinsApiFavoritesInteractorImpl,
    ): CoinsFavoritesInteractor

}