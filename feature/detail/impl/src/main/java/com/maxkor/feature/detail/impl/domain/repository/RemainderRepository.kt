package com.maxkor.feature.detail.impl.domain.repository

import com.maxkor.feature.detail.impl.domain.model.CoinReminder

interface RemainderRepository {

    fun createReminder(
        coinReminder: CoinReminder,
        onIncorrectTimeInput: (String) -> Unit,
    )
}