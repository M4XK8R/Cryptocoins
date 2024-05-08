package com.maxkor.feature.detail.impl.domain.preferences

import com.maxkor.feature.detail.api.domain.model.ExtraCoinInfo


interface DetailPreferences {

    fun saveCoinExtraInfo(
        key: String,
        extraInfo: String,
    )

    fun getExtraCoinInfo(key: String) : ExtraCoinInfo
}