package com.maxkor.core.base.domain.service

interface PermissionsCheckerService {

    val isWriteStorageGranted: Boolean

    val isPostNotificationsGranted: Boolean

    sealed class PermissionStatus() {
        data object IsGranted : PermissionStatus()
        data object IsNotGranted : PermissionStatus()
    }
}