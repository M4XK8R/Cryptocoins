package com.maxkor.core.base.data.repository

import com.maxkor.core.base.domain.repository.CheckerRepository
import com.maxkor.core.base.domain.service.InternetCheckerService
import com.maxkor.core.base.domain.service.PermissionsCheckerService
import javax.inject.Inject

class CheckerRepositoryImpl @Inject constructor(
    private val permissionsCheckerService: PermissionsCheckerService,
    private val internetCheckerService: InternetCheckerService,
) : CheckerRepository {
    override fun checkNotificationPermission(): PermissionsCheckerService.PermissionStatus =
        when (permissionsCheckerService.isPostNotificationsGranted) {
            true -> PermissionsCheckerService.PermissionStatus.IsGranted
            false -> PermissionsCheckerService.PermissionStatus.IsNotGranted
        }

    override fun checkStoragePermission(): PermissionsCheckerService.PermissionStatus =
        when (permissionsCheckerService.isWriteStorageGranted) {
            true -> PermissionsCheckerService.PermissionStatus.IsGranted
            false -> PermissionsCheckerService.PermissionStatus.IsNotGranted
        }

    override fun checkInternetConnection(): Boolean =
        internetCheckerService.isWifiAvailable ||
                internetCheckerService.isMobileInternetAvailable
}