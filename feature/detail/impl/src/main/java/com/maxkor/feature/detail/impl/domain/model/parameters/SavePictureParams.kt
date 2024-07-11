package com.maxkor.feature.detail.impl.domain.model.parameters

import com.maxkor.feature.detail.impl.domain.model.DownloadableImage

data class SavePictureParams(
    val downloadableImage: DownloadableImage,
    val noPostNotificationPermissionCase: () -> Unit,
    val noWriteStoragePermissionCase: () -> Unit,
    val onDownloadState: (message: String) -> Unit,
)