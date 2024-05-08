package com.maxkor.feature.detail.impl.data.repository

import android.content.Context
import com.maxkor.core.base.domain.service.NotificationService
import com.maxkor.core.base.util.createDebugLog
import com.maxkor.feature.detail.impl.R
import com.maxkor.feature.detail.impl.data.di.qualifiers.DetailFeatureImplementation
import com.maxkor.feature.detail.impl.domain.model.CoinReminder
import com.maxkor.feature.detail.impl.domain.repository.RemainderRepository
import com.maxkor.feature.detail.impl.domain.service.AlarmService
import com.maxkor.feature.detail.impl.domain.util.getCalendar
import com.maxkor.feature.detail.impl.domain.util.getCalendarTime
import com.maxkor.feature.detail.impl.domain.util.setUpCalendar
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RemainderRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @DetailFeatureImplementation private val notificationService: NotificationService,
    private val alarmService: AlarmService,
) : RemainderRepository {

    override fun createReminder(
        coinReminder: CoinReminder,
        onIncorrectTimeInput: (String) -> Unit,
    ) {
        val calendar = getCalendar()
        createDebugLog("now is ${calendar.time}")
        val currentTime = calendar.getCalendarTime()
        calendar.setUpCalendar(
            hour = coinReminder.hour,
            minute = coinReminder.minute
        )
        val targetTime = calendar.getCalendarTime()
        if (targetTime <= currentTime) {
            onIncorrectTimeInput(
                context.getString(R.string.time_has_already_passed)
            )
        } else {
            alarmService.createAlarm(
                detailCoin = coinReminder.detailCoin,
                time = targetTime
            )
            createAndShowNotification(
                contentText = context.getString(
                    R.string.remind_about_coin,
                    coinReminder.detailCoin.name,
                    calendar.time
                )
            )
        }
    }

    /**
     * Private sector
     */
    private fun createAndShowNotification(contentText: String): Unit =
        with(notificationService) {
            createNotification(
                contentText = contentText,
                contentIntent = null
            ).also { showNotification(it) }
        }
}