package com.maxkor.feature.detail.impl.domain.repository

import com.maxkor.feature.detail.impl.domain.model.DownloadableImage

interface ImageRepository {

    fun savePicture(
        downloadableImage: DownloadableImage,
        onDownloadState: (message: String) -> Unit,
    )

    fun sharePicture(url: String)
}