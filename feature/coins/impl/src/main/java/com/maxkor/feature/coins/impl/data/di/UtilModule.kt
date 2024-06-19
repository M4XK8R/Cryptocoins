package com.maxkor.feature.coins.impl.data.di

import com.maxkor.feature.coins.impl.data.util.TokenBucket
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilModule {

    @Provides
    @Singleton
    fun provideTokenBucket(): TokenBucket =
        TokenBucket(
            capacity = TokenBucket.Defaults.CAPACITY,
            refillTokens = TokenBucket.Defaults.REFILL_TOKENS,
            refillTime = TokenBucket.Defaults.REFILL_TIME,
            timeUnit = TokenBucket.Defaults.timeUnit
        )
}