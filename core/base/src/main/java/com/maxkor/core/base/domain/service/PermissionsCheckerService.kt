package com.maxkor.core.base.domain.service

interface PermissionsCheckerService {

    fun isWriteStorageGranted(): Boolean

    fun isPostNotificationsGranted(): Boolean
}