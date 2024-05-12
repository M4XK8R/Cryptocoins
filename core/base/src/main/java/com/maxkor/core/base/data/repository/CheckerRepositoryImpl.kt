package com.maxkor.core.base.data.repository

import com.maxkor.core.base.domain.repository.CheckerRepository
import com.maxkor.core.base.domain.service.InternetCheckerService
import com.maxkor.core.base.domain.service.PermissionsCheckerService
import javax.inject.Inject

class CheckerRepositoryImpl @Inject constructor(
    private val permissionsCheckerService: PermissionsCheckerService,
    private val internetCheckerService: InternetCheckerService,
) : CheckerRepository {
    override fun hasNotificationPermission(): Boolean =
     permissionsCheckerService.isPostNotificationsGranted()

    override fun hasStoragePermission(): Boolean =
       permissionsCheckerService.isWriteStorageGranted()

    override fun hasInternetConnection(): Boolean =
        internetCheckerService.isWifiAvailable() ||
                internetCheckerService.isMobileInternetAvailable()
}