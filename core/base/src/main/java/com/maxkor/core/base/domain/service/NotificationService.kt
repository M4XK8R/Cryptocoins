package com.maxkor.core.base.domain.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import com.maxkor.core.base.R

interface NotificationService {

    fun createNotification(
        contentText: String,
        contentIntent: PendingIntent?,
    ): Notification

    fun showNotification(notification: Notification)

    companion object {
        private const val CHANNEL_NAME = "channel_name"
        const val CHANNEL_ID = "channel_id"
        const val NOTIFICATION_ID = 9738

        fun createCustomNotificationChannel(
            context: Context,
            channelId: String = CHANNEL_ID,
            channelName: String = CHANNEL_NAME,
            importanceLevel: Int = NotificationManager.IMPORTANCE_DEFAULT,
            channelDescription: String = context.getString(R.string.channel_description),
        ): NotificationChannel = NotificationChannel(
            channelId,
            channelName,
            importanceLevel
        ).also { it.description = channelDescription }
    }
}