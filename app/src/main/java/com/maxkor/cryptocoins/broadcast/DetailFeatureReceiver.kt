package com.maxkor.cryptocoins.broadcast

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.maxkor.core.base.utils.createDebugLog
import com.maxkor.feature.detail.api.domain.service.DetailFeatureAlarmService
import com.maxkor.feature.detail.api.domain.service.DetailFeatureNotificationService
import com.maxkor.feature.mainactivity.impl.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFeatureReceiver : BroadcastReceiver() {

    @Inject
    lateinit var notificationService: DetailFeatureNotificationService

    override fun onReceive(
        context: Context?,
        intent: Intent?,
    ) {
        createDebugLog("onReceive")
        val coinName = intent?.getStringExtra(
            DetailFeatureAlarmService.ITEM_NAME_KEY
        ) ?: UNKNOWN_VALUE

        val launchMainActivityIntent = Intent(
            context,
            MainActivity::class.java
        ).apply {
            action = ACTION_SHOW_REQUIRED_SCREEN
            putExtra(NAME_PARAM, coinName)
        }
        val contentIntent = PendingIntent.getActivity(
            context,
            0,
            launchMainActivityIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        notificationService.showNotification(
            contentText = "Aaaaaa",
            contentIntent = contentIntent
        )
    }

    companion object {
        private const val UNKNOWN_VALUE = "Unknown"
        const val ACTION_SHOW_REQUIRED_SCREEN = "show_detail_screen"
        const val NAME_PARAM = "data_model_id"
        const val ID_DEFAULT_VALUE = "-1"
    }
}