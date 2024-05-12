package com.maxkor.core.base.data.service

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.maxkor.core.base.domain.service.InternetCheckerService
import javax.inject.Inject

class InternetCheckerServiceImpl @Inject constructor(
    private val connectivityManager: ConnectivityManager,
) : InternetCheckerService {

    override fun isMobileInternetAvailable(): Boolean =
        hasConnection(ConnectionType.MobileInternet) ?: false

    override fun isWifiAvailable(): Boolean =
        hasConnection(ConnectionType.Wifi) ?: false

    /**
     * Private sector
     */
    private fun hasConnection(connectionType: ConnectionType): Boolean? =
        getNetworkCapabilities()?.hasTransport(connectionType.transport)

    private fun getNetworkCapabilities(): NetworkCapabilities? =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

    private sealed class ConnectionType(val transport: Int) {
        data object MobileInternet : ConnectionType(NetworkCapabilities.TRANSPORT_CELLULAR)
        data object Wifi : ConnectionType(NetworkCapabilities.TRANSPORT_WIFI)
    }
}
