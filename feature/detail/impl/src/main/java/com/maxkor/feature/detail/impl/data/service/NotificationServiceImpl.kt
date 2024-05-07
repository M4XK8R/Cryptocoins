package com.maxkor.feature.detail.impl.data.service

import android.app.Notification
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
            createNotificationChannel(
                NotificationService.createCustomNotificationChannel(context)
            )
            notify(NotificationService.NOTIFICATION_ID, notification)
        }

    override fun createNotification(
        contentText: String,
        contentIntent: PendingIntent?,
    ): Notification = NotificationCompat
        .Builder(context, NotificationService.CHANNEL_ID)
        .setSmallIcon(CryptocoinsImages.Notify)
        .setContentText(contentText)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setAutoCancel(true)
        .build()
}