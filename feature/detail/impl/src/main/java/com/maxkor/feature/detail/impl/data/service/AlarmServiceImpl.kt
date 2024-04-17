package com.maxkor.feature.detail.impl.data.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.maxkor.feature.detail.api.broadcast.DetailFeatureReceiver
import com.maxkor.feature.detail.impl.domain.service.AlarmService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AlarmServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val alarmManager: AlarmManager,
    private val receiver: DetailFeatureReceiver,
) : AlarmService {

    override fun createAlarm(
        coinName: String,
        coinPrice: String,
        coinImageUrl: String,
        time: Long,
    ) = alarmManager.set(
        AlarmManager.RTC,
        time,
        createPendingAlarmIntent(
            alarmIntent = createAlarmIntent(
                coinName = coinName,
                coinPrice = coinPrice,
                coinImageUrl = coinImageUrl
            )
        )
    )

    /**
     * Private sector
     */
    private fun createPendingAlarmIntent(
        alarmIntent: Intent,
    ): PendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        alarmIntent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    private fun createAlarmIntent(
        coinName: String,
        coinPrice: String,
        coinImageUrl: String,
    ): Intent = Intent(
        context,
        receiver::class.java
    ).apply {
        putExtra(AlarmService.COIN_NAME_KEY, coinName)
        putExtra(AlarmService.COIN_PRICE_KEY, coinPrice)
        putExtra(AlarmService.COIN_IMAGE_URL_KEY, coinImageUrl)
    }
}