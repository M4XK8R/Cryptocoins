package com.maxkor.feature.detail.impl.data.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.maxkor.feature.detail.api.broadcast.DetailFeatureReceiver
import com.maxkor.feature.detail.impl.domain.service.AlarmService
import dagger.hilt.android.qualifiers.ApplicationContext
import org.jetbrains.annotations.NotNull
import java.util.Calendar
import javax.inject.Inject

class AlarmServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val receiver: DetailFeatureReceiver,
) : AlarmService {

    override fun createAlarm(
        coinName: String,
        coinPrice: String,
        coinImageUrl: String,
        time: Long,
    ) {
        val alarmIntent = Intent(
            context,
            receiver::class.java
        ).apply {
            putExtra(AlarmService.COIN_NAME_KEY, coinName)
            putExtra(AlarmService.COIN_PRICE_KEY, coinPrice)
            putExtra(AlarmService.COIN_IMAGE_URL_KEY, coinImageUrl)
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