package com.maxkor.feature.detail.impl.data.preferences

import android.content.SharedPreferences
import com.maxkor.feature.detail.api.domain.model.ExtraCoinInfo
import com.maxkor.feature.detail.impl.domain.preferences.DetailPreferences
import javax.inject.Inject

class DetailPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : DetailPreferences {

    override fun saveCoinExtraInfo(
        key: String,
        extraInfo: String,
    ) = sharedPreferences
        .edit()
        .putString(key, extraInfo)
        .apply()

    override fun getExtraCoinInfo(key: String): ExtraCoinInfo =
        ExtraCoinInfo(
            value = sharedPreferences.getString(
                key,
                null
            ) ?: ""
        )
}
