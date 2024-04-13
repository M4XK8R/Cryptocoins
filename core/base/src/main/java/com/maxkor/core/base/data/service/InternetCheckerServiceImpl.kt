package com.maxkor.core.base.data.service

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.maxkor.core.base.domain.service.InternetCheckerService
import javax.inject.Inject

class InternetCheckerServiceImpl @Inject constructor(
    private val connectivityManager: ConnectivityManager,
) : InternetCheckerService {

    override val isMobileInternetAvailable: Boolean
        get() = isConnectionAvailable(ConnectionType.MobileInternet) ?: false

    override val isWifiAvailable: Boolean
        get() = isConnectionAvailable(ConnectionType.Wifi) ?: false

    /**
     * Private sector
     */

    private fun isConnectionAvailable(
        connectionType: ConnectionType,
    ): Boolean? = getNetworkCapabilities()
        ?.hasTransport(connectionType.transport)

    private fun getNetworkCapabilities(): NetworkCapabilities? =
        connectivityManager.getNetworkCapabilities(
            connectivityManager.activeNetwork
        )

    private sealed class ConnectionType(val transport: Int) {
        data object MobileInternet : ConnectionType(
            NetworkCapabilities.TRANSPORT_CELLULAR
        )

        data object Wifi : ConnectionType(
            NetworkCapabilities.TRANSPORT_WIFI
        )
    }
}
