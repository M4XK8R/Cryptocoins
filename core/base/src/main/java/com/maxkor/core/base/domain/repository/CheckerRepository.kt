package com.maxkor.core.base.domain.repository

interface CheckerRepository {

    fun checkNotificationPermission(): PermissionStatus

    fun checkStoragePermission(): PermissionStatus

    fun checkInternetConnection()

    companion object {
        fun onPermissionStateAction(
            condition: PermissionStatus,
            hasPermissionCase: () -> Unit,
            noPermissionCase: () -> Unit,
        ) = when (condition) {
            PermissionStatus.IsGranted -> hasPermissionCase()
            PermissionStatus.IsNotGranted -> noPermissionCase()
        }
    }

    sealed class PermissionStatus() {
        data object IsGranted : PermissionStatus()
        data object IsNotGranted : PermissionStatus()
    }
}