package com.maxkor.core.base.data.repository

import com.maxkor.core.base.domain.repository.CheckerRepository
import com.maxkor.core.base.domain.service.PermissionsCheckerService
import javax.inject.Inject

class CheckerRepositoryImpl @Inject constructor(
    private val permissionsCheckerService: PermissionsCheckerService,
) : CheckerRepository {
    override fun checkNotificationPermission(): CheckerRepository.PermissionStatus =
        when (permissionsCheckerService.isPostNotificationsGranted) {
            true -> CheckerRepository.PermissionStatus.IsGranted
            false -> CheckerRepository.PermissionStatus.IsNotGranted
        }

    override fun checkStoragePermission(): CheckerRepository.PermissionStatus =
        when (permissionsCheckerService.isWriteStorageGranted) {
            true -> CheckerRepository.PermissionStatus.IsGranted
            false -> CheckerRepository.PermissionStatus.IsNotGranted
        }

    override fun checkInternetConnection() {
        TODO("Not yet implemented")
    }
}