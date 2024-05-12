package com.maxkor.core.base.domain.repository

interface CheckerRepository {

    fun hasNotificationPermission(): Boolean

    fun hasStoragePermission(): Boolean

    fun hasInternetConnection(): Boolean

    companion object {
        fun onPermissionState(
            hasPermission: Boolean,
            hasPermissionCase: () -> Unit,
            noPermissionCase: () -> Unit,
        ) = when (hasPermission) {
            true -> hasPermissionCase()
            false -> noPermissionCase()
        }
    }
}
