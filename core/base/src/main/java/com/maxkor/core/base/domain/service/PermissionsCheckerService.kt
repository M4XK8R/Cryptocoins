package com.maxkor.core.base.domain.service

interface PermissionsCheckerService {

    val isWriteStorageGranted: Boolean

    val isPostNotificationsGranted: Boolean
}