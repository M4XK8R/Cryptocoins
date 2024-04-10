package com.maxkor.cryptocoins.broadcast

import com.maxkor.feature.detail.api.broadcast.DetailFeatureReceiver
import com.maxkor.feature.detail.impl.domain.service.AlarmService
import com.maxkor.feature.mainactivity.impl.presentation.MainActivity
import javax.inject.Inject

class DetailFeatureReceiverImpl @Inject constructor() :
    DetailFeatureReceiver(
        coinNameKey = AlarmService.COIN_NAME_KEY,
        coinPriceKey = AlarmService.COIN_PRICE_KEY,
        coinImageUrlKey = AlarmService.COIN_IMAGE_URL_KEY,
        activityToLaunch = MainActivity()
    )