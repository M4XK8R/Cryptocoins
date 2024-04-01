package com.maxkor.feature.detail.impl.domain.service

interface DownloadImageService {

    fun savePicture(
        url: String,
        saveName: String,
    )
}