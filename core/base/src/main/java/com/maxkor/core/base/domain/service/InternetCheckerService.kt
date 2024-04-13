package com.maxkor.core.base.domain.service

interface InternetCheckerService {

    val isMobileInternetAvailable: Boolean

    val isWifiAvailable: Boolean
}