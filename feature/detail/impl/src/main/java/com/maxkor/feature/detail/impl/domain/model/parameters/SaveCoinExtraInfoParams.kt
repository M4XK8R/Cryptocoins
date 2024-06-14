package com.maxkor.feature.detail.impl.domain.model.parameters

import com.maxkor.feature.detail.impl.domain.model.ExtraDetailCoinInfo

data class SaveCoinExtraInfoParams(
    val key: String,
    val extraInfo: ExtraDetailCoinInfo,
)