package com.maxkor.cryptocoins.service.impl

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.maxkor.cryptocoins.broadcast.DetailFeatureReceiverImpl
import com.maxkor.feature.detail.api.domain.service.DetailFeatureAlarmService
import dagger.hilt.android.qualifiers.ApplicationContext
import org.jetbrains.annotations.NotNull
import java.util.Calendar
import javax.inject.Inject

class DetailFeatureAlarmServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : DetailFeatureAlarmService {

    override fun createAlarm(
        coinName: String,
        coinPrice: String,
        coinImageUrl: String,
        time: Long,
    ) {
        val alarmIntent = Intent(
            context,
            DetailFeatureReceiverImpl::class.java
        ).apply {
            putExtra(DetailFeatureAlarmService.COIN_NAME_KEY, coinName)
            putExtra(DetailFeatureAlarmService.COIN_PRICE_KEY, coinPrice)
            putExtra(DetailFeatureAlarmService.COIN_IMAGE_URL_KEY, coinImageUrl)
        }

        @NotNull val pendingAlarmIntent = PendingIntent.getBroadcast(
            context,
            0,
            alarmIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val currentTime = Calendar.getInstance().timeInMillis
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(
            AlarmManager.RTC,
            currentTime + time,
            pendingAlarmIntent
        )
    }
}