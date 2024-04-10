package com.maxkor.core.base.domain.service

import android.app.PendingIntent

interface NotificationService {

    fun showNotification(
        contentText: String,
        contentIntent: PendingIntent?,
    )

    companion object {
        const val CHANNEL_ID = "channel_id"
        const val NOTIFICATION_ID = 9738
    }
}