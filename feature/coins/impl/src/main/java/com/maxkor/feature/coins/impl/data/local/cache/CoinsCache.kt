package com.maxkor.feature.coins.impl.data.local.cache

import androidx.collection.LruCache
import com.maxkor.feature.coins.impl.data.local.cache.CoinsCache.Keys.COINS_KEY
import com.maxkor.feature.coins.impl.data.local.model.CoinCached

class CoinsCache {

    private val maxMemory = Runtime
        .getRuntime()
        .maxMemory()
    private val maxMemoryInKilobytes = (maxMemory / 1024).toInt()
    private val cacheSize = maxMemoryInKilobytes / 8

    private val cache = LruCache<String, List<CoinCached>>(cacheSize)

    fun saveCoins(cachedCoins: List<CoinCached>) {
        cache.put(COINS_KEY, cachedCoins)
    }

    fun getCoins(): List<CoinCached>? {
        return cache[COINS_KEY]
    }

    private object Keys {
        const val COINS_KEY = " coins_key"
    }
}