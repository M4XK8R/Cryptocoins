package com.maxkor.core.base.domain.repository

import com.maxkor.core.base.domain.service.PermissionsCheckerService

interface CheckerRepository {

    fun checkNotificationPermission(): PermissionsCheckerService.PermissionStatus

    fun checkStoragePermission(): PermissionsCheckerService.PermissionStatus

    fun checkInternetConnection(): Boolean

    companion object {
        fun onPermissionStateAction(
            condition: PermissionsCheckerService.PermissionStatus,
            hasPermissionCase: () -> Unit,
            noPermissionCase: () -> Unit,
        ) = when (condition) {
            PermissionsCheckerService.PermissionStatus.IsGranted -> hasPermissionCase()
            PermissionsCheckerService.PermissionStatus.IsNotGranted -> noPermissionCase()
        }
    }
}