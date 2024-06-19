package com.maxkor.feature.coins.impl.data.util

import java.util.concurrent.TimeUnit
import kotlin.math.min

class TokenBucket(
    private val capacity: Int,
    private val refillTokens: Int,
    private val refillTime: Long,
    private val timeUnit: TimeUnit,
) {
    private var tokens: Int = capacity
    private var lastRefillTimestamp: Long = System.currentTimeMillis()

    fun tryAcquire(): Boolean {
        refill()
        synchronized(this) {
            if (tokens > 0) {
                tokens--
                return true
            }
            return false
        }
    }

    private fun refill() {
        val now = System.currentTimeMillis()
        val timePassed = now - lastRefillTimestamp
        val tokensToAdd = (timePassed * refillTokens) / timeUnit.toMillis(refillTime)
        if (tokensToAdd > 0) {
            tokens = min(capacity, tokens + tokensToAdd.toInt())
            lastRefillTimestamp = now
        }
    }

    object Defaults {
        const val CAPACITY = 1
        const val REFILL_TOKENS = 1
        const val REFILL_TIME = 15L
        val timeUnit = TimeUnit.SECONDS
    }
}