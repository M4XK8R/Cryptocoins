package com.maxkor.cryptocoins.broadcast

import com.maxkor.feature.detail.api.presentation.broadcast.DetailFeatureReceiver
import com.maxkor.feature.detail.impl.domain.service.AlarmService
import com.maxkor.feature.mainactivity.impl.presentation.MainActivity
import javax.inject.Inject

class DetailFeatureReceiverImpl @Inject constructor() :
    DetailFeatureReceiver(
        coinNameKey = AlarmService.COIN_NAME_KEY,
        activityToLaunch = MainActivity()
    )