package com.maxkor.feature.mainactivity.impl.presentation.ktx

import com.maxkor.feature.detail.api.broadcast.DetailFeatureReceiver
import com.maxkor.feature.mainactivity.impl.presentation.model.ReceivedCoinDataVo

fun ReceivedCoinDataVo.isDataNotUnknown(): Boolean = when {
    this.name == DetailFeatureReceiver.UNKNOWN -> false
    else -> true
}