package com.maxkor.feature.detail.impl.domain.repository

import android.app.PendingIntent

interface RemainderRepository {

    fun showNotification(
        contentText: String,
        contentIntent: PendingIntent?,
    )

    fun createAlarm(
        coinName: String,
        coinPrice: String,
        coinImageUrl: String,
        time: Long,
    )
}