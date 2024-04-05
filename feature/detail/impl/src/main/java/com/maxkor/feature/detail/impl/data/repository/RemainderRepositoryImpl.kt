package com.maxkor.feature.detail.impl.data.repository

import android.app.PendingIntent
import android.content.Context
import com.maxkor.feature.detail.api.domain.service.DetailFeatureAlarmService
import com.maxkor.feature.detail.api.domain.service.DetailFeatureNotificationService
import com.maxkor.feature.detail.impl.domain.repository.RemainderRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RemainderRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val notificationService: DetailFeatureNotificationService,
    private val alarmService: DetailFeatureAlarmService,
) : RemainderRepository {

    override fun showNotification(
        contentText: String,
        contentIntent: PendingIntent?,
    ) = notificationService.showNotification(
        contentText = contentText,
        contentIntent = contentIntent
    )

    override fun createAlarm(
        coinName: String,
        coinPrice: String,
        coinImageUrl: String,
        time: Long,
    ) = alarmService.createAlarm(
        coinName = coinName,
        coinPrice = coinPrice,
        coinImageUrl = coinImageUrl,
        time = time
    )
}