package com.maxkor.feature.detail.api.domain.service

interface DetailFeatureAlarmService {

    fun createAlarm(
        name: String,
        time: Long,
    )

    companion object {
        const val ITEM_NAME_KEY = "item_name"
    }
}