package com.maxkor.core.base.data.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat
import com.maxkor.core.base.data.images.CryptocoinsImages
import com.maxkor.core.base.domain.service.NotificationService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NotificationServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val notificationManager: NotificationManager,
) : NotificationService {

    override fun showNotification(notification: Notification) =
        notificationManager.run {
            createNotificationChannel(createCustomNotificationChannel())
            notify(NOTIFICATION_ID, notification)
        }

    override fun createNotification(
        contentText: String,
        contentIntent: PendingIntent?,
    ): Notification = NotificationCompat
        .Builder(context, CHANNEL_ID)
        .setSmallIcon(CryptocoinsImages.Notify)
        .setContentText(contentText)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setContentIntent(contentIntent)
        .setAutoCancel(true)
        .build()

    /**
     * Private sector
     */
    private fun createCustomNotificationChannel(
        channelId: String = CHANNEL_ID,
        channelName: String = "channel name",
        importanceLevel: Int = NotificationManager.IMPORTANCE_DEFAULT,
        channelDescription: String = "channel description",
    ): NotificationChannel = NotificationChannel(
        channelId,
        channelName,
        importanceLevel
    ).also { it.description = channelDescription }

    /**
     * Companion
     */
    companion object {
        private const val CHANNEL_ID = "channel_id"
        private const val NOTIFICATION_ID = 9738
    }
}