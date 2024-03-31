package com.maxkor.feature.detail.impl.data.repository

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import androidx.core.app.NotificationCompat
import com.maxkor.core.ui.images.CryptocoinsImages
import com.maxkor.feature.detail.impl.domain.repository.RemainderRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RemainderRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : RemainderRepository {

    override fun showNotification(
        contentText: String,
        contentIntent: PendingIntent?,
    ) {
        val notification = NotificationCompat
            .Builder(context, RemainderRepository.CHANNEL_ID)
//            .setSmallIcon(R.drawable.baseline_circle_notifications_24)
            .setSmallIcon(CryptocoinsImages.Notify)
            .setContentText(contentText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(contentIntent)
            .setAutoCancel(true)
            .build()

        val channel = NotificationChannel(
            RemainderRepository.CHANNEL_ID,
            "channel name",
            NotificationManager.IMPORTANCE_DEFAULT
        ).also { it.description = "channel description" }

        (context.getSystemService(
            Service.NOTIFICATION_SERVICE
        ) as NotificationManager).run {
            createNotificationChannel(channel)
            notify(RemainderRepository.NOTIFICATION_ID, notification)
        }
    }

    override fun createAlarm() {
        TODO("Not yet implemented")
    }
}