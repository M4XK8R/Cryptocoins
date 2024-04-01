package com.maxkor.feature.detail.api.domain.service

import android.app.PendingIntent

interface DetailFeatureNotificationService {

    fun showNotification(
        contentText: String,
        contentIntent: PendingIntent?,
    )

    companion object {
        const val CHANNEL_ID = "channel_id"
        const val NOTIFICATION_ID = 9738
    }
}