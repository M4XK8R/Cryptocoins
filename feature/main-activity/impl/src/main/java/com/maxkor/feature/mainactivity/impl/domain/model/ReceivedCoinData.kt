package com.maxkor.feature.mainactivity.impl.domain.model

import com.maxkor.feature.detail.api.broadcast.DetailFeatureReceiver

data class ReceivedCoinData(
    val name: String?,
    val price: String?,
    val imageUrl: String?,
) {
    companion object {
        fun validate(receivedCoinData: ReceivedCoinData): Boolean = when {
            receivedCoinData.name == DetailFeatureReceiver.UNKNOWN -> false
            receivedCoinData.price == DetailFeatureReceiver.UNKNOWN -> false
            receivedCoinData.imageUrl == DetailFeatureReceiver.UNKNOWN -> false
            else -> true
        }
    }
}

