package com.maxkor.core.base.data.service

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.maxkor.core.base.domain.service.PermissionsCheckerService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PermissionsCheckerServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : PermissionsCheckerService {

    override val isWriteStorageGranted: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R ||
                checkIfPermissionIsGranted(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )

    override val isPostNotificationsGranted: Boolean
        get() = Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
                checkIfPermissionIsGranted(
                    Manifest.permission.POST_NOTIFICATIONS
                )

    /**
     * Private functions
     */

    private fun checkIfPermissionIsGranted(permission: String): Boolean =
        ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
}

