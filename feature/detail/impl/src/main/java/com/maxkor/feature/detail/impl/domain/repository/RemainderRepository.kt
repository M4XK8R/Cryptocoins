package com.maxkor.feature.detail.impl.domain.repository

import android.app.PendingIntent

interface RemainderRepository {

    fun showNotification(
        contentText: String,
        contentIntent: PendingIntent?,
    )

    fun createAlarm()

    companion object {
        const val CHANNEL_ID = "channel_id"
        const val NOTIFICATION_ID = 9738
    }
}