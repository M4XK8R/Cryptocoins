package com.maxkor.feature.detail.impl.data.repository

import android.app.PendingIntent
import com.maxkor.feature.detail.impl.domain.service.AlarmService
import com.maxkor.core.base.domain.service.NotificationService
import com.maxkor.feature.detail.impl.data.di.qualifiers.DetailFeatureImplementation
import com.maxkor.feature.detail.impl.domain.repository.RemainderRepository
import javax.inject.Inject

class RemainderRepositoryImpl @Inject constructor(
    @DetailFeatureImplementation private val notificationService: NotificationService,
    private val alarmService: AlarmService,
) : RemainderRepository {

    override fun createAndShowNotification(
        contentText: String,
        contentIntent: PendingIntent?,
    ): Unit = with(notificationService) {
        createNotification(
            contentText = contentText,
            contentIntent = contentIntent
        ).also { showNotification(it) }
    }

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