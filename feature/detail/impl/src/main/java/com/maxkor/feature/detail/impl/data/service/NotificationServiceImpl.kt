package com.maxkor.feature.detail.impl.data.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import androidx.core.app.NotificationCompat
import com.maxkor.core.base.data.images.CryptocoinsImages
import com.maxkor.core.base.domain.service.NotificationService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NotificationServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : NotificationService {

    override fun showNotification(
        contentText: String,
        contentIntent: PendingIntent?,
    ) {
        val notification = NotificationCompat
            .Builder(context, NotificationService.CHANNEL_ID)
            .setSmallIcon(CryptocoinsImages.Notify)
            .setContentText(contentText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        val channel = NotificationChannel(
            NotificationService.CHANNEL_ID,
            "channel name",
            NotificationManager.IMPORTANCE_DEFAULT
        ).also { it.description = "channel description" }

        val notificationManager = context.getSystemService(
            Service.NOTIFICATION_SERVICE
        ) as NotificationManager
        notificationManager.run {
            createNotificationChannel(channel)
            notify(NotificationService.NOTIFICATION_ID, notification)
        }
    }
}