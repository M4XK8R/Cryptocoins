package com.maxkor.cryptocoins.broadcast

import com.maxkor.feature.detail.api.broadcast.DetailFeatureReceiver
import com.maxkor.feature.mainactivity.impl.presentation.MainActivity

class DetailFeatureReceiverImpl : DetailFeatureReceiver(
    activityToLaunch = MainActivity()
)