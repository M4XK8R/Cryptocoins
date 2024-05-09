package com.maxkor.feature.mainactivity.impl.presentation.model

import com.maxkor.feature.detail.api.broadcast.DetailFeatureReceiver

data class ReceivedCoinDataVo(val name: String)

fun ReceivedCoinDataVo.isDataNotUnknown(): Boolean = when {
    this.name == DetailFeatureReceiver.UNKNOWN -> false
    else -> true
}