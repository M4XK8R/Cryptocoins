package com.maxkor.feature.detail.api.broadcast

import android.app.Activity
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.maxkor.core.base.domain.service.NotificationService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class DetailFeatureReceiver(
    private val coinNameKey: String,
    private val coinPriceKey: String,
    private val coinImageUrlKey: String,
    private val activityToLaunch: Activity,
) : BroadcastReceiver() {

    @Inject
    lateinit var notificationService: NotificationService

    override fun onReceive(
        context: Context?,
        intent: Intent?,
    ) {
        val coinName = intent?.getStringExtra(coinNameKey)
        val coinPrice = intent?.getStringExtra(coinPriceKey)
        val coinImageUrl = intent?.getStringExtra(coinImageUrlKey)

        val launchActivityIntent = Intent(
            context,
            activityToLaunch::class.java
        ).apply {
            action = ACTION_SHOW_REQUIRED_SCREEN
            putExtra(COIN_NAME_PARAM, coinName)
            putExtra(COIN_PRICE_PARAM, coinPrice)
            putExtra(COIN_IMAGE_URL_PARAM, coinImageUrl)
        }

        val contentIntent = PendingIntent.getActivity(
            context,
            0,
            launchActivityIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        notificationService.showNotification(
            contentText = coinName ?: UNKNOWN,
            contentIntent = contentIntent
        )
    }

    companion object {
        const val UNKNOWN = "Unknown"
        const val ACTION_SHOW_REQUIRED_SCREEN = "show_detail_screen"
        const val COIN_NAME_PARAM = "coin_name"
        const val COIN_PRICE_PARAM = "coin_price"
        const val COIN_IMAGE_URL_PARAM = "coin_image_url"
    }
}