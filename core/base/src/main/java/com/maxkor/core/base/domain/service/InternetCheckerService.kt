package com.maxkor.core.base.domain.service

interface InternetCheckerService {

    fun isMobileInternetAvailable(): Boolean

    fun isWifiAvailable(): Boolean
}