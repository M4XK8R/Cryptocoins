package com.maxkor.feature.mainactivity.impl.presentation.mapper

import com.maxkor.feature.detail.api.broadcast.DetailFeatureReceiver
import com.maxkor.feature.mainactivity.impl.domain.model.ReceivedCoinData
import com.maxkor.feature.mainactivity.impl.presentation.model.ReceivedCoinDataVo

fun ReceivedCoinData.toReceivedCoinDataVo() = ReceivedCoinDataVo(
    name = name ?: DetailFeatureReceiver.UNKNOWN,
    price = price ?: DetailFeatureReceiver.UNKNOWN,
    imageUrl = imageUrl ?: DetailFeatureReceiver.UNKNOWN
)

fun ReceivedCoinDataVo.toReceivedCoinData() = ReceivedCoinData(
    name = name,
    price = price,
    imageUrl = imageUrl
)