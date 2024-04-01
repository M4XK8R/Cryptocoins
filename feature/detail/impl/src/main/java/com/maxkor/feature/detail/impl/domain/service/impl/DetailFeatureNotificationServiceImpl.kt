package com.maxkor.feature.detail.impl.domain.service.impl

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import androidx.core.app.NotificationCompat
import com.maxkor.core.ui.images.CryptocoinsImages
import com.maxkor.feature.detail.api.domain.service.DetailFeatureNotificationService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DetailFeatureNotificationServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : DetailFeatureNotificationService {

    override fun showNotification(
        contentText: String,
        contentIntent: PendingIntent?,
    ) {
        val notification = NotificationCompat
            .Builder(context, DetailFeatureNotificationService.CHANNEL_ID)
            .setSmallIcon(CryptocoinsImages.Notify)
            .setContentText(contentText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(contentIntent)
            .setAutoCancel(true)
            .build()

        val channel = NotificationChannel(
            DetailFeatureNotificationService.CHANNEL_ID,
            "channel name",
            NotificationManager.IMPORTANCE_DEFAULT
        ).also { it.description = "channel description" }

        val notificationManager = context.getSystemService(
            Service.NOTIFICATION_SERVICE
        ) as NotificationManager
        notificationManager.run {
            createNotificationChannel(channel)
            notify(DetailFeatureNotificationService.NOTIFICATION_ID, notification)
        }
    }
}