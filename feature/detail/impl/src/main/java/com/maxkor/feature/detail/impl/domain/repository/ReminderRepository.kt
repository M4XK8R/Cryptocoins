package com.maxkor.feature.detail.impl.domain.repository

import com.maxkor.feature.detail.impl.domain.model.CoinReminder

interface ReminderRepository {

    fun createReminder(
        coinReminder: CoinReminder,
        onIncorrectTimeInput: (String) -> Unit,
    )
}