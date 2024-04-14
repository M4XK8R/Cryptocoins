package com.maxkor.core.base.domain.service

import android.app.Notification
import android.app.PendingIntent

interface NotificationService {

    fun createNotification(
        contentText: String,
        contentIntent: PendingIntent?,
    ): Notification

    fun showNotification(notification: Notification)
}