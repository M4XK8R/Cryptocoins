package com.maxkor.feature.detail.impl.domain.repository

interface ImageRepository {

    fun savePicture(
        url: String,
        saveName: String,
        onDownloadState: (message: String) -> Unit,
    )

    fun sharePicture(url: String)
}