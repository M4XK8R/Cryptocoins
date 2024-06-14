package com.maxkor.feature.detail.impl.domain.model.parameters

import com.maxkor.feature.detail.impl.domain.model.CoinReminder

data class CreateReminderParams(
    val coinReminder: CoinReminder,
    val noPostNotificationPermissionCase: () -> Unit,
    val onIncorrectTimeInput: (String) -> Unit,
)