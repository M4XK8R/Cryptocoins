package com.maxkor.feature.detail.impl.domain.preferences

import com.maxkor.feature.coins.api.domain.model.ExtraDetailCoinInfo

interface DetailPreferences {

    fun saveCoinExtraInfo(
        key: String,
        extraInfo: String,
    )

    fun getExtraCoinInfo(key: String) : ExtraDetailCoinInfo
}