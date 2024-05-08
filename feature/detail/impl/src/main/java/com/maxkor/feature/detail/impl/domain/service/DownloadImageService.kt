package com.maxkor.feature.detail.impl.domain.service

import com.maxkor.feature.detail.impl.domain.model.DownloadableImage

interface DownloadImageService {

    fun savePicture(
        downloadableImage: DownloadableImage,
        isNetworkAvailable: Boolean,
        onDownloadState: (message: String) -> Unit,
    )
}