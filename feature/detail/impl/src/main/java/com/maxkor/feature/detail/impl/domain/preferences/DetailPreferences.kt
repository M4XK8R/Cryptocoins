package com.maxkor.feature.detail.impl.domain.preferences

interface DetailPreferences {

    fun saveCoinExtraInfo(
        key: String,
        extraInfo: String,
    )

    fun getCoinExtraInfo(key: String) : String
}