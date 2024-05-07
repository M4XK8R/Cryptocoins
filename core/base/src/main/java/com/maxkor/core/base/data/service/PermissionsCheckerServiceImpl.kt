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

    override fun isWriteStorageGranted(): Boolean =
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.R ||
                isPermissionGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun isPostNotificationsGranted(): Boolean =
        Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
                isPermissionGranted(Manifest.permission.POST_NOTIFICATIONS)

    /**
     * Private sector
     */
    private fun isPermissionGranted(permission: String): Boolean =
        ContextCompat.checkSelfPermission(context, permission) ==
                PackageManager.PERMISSION_GRANTED
}

