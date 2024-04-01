package com.maxkor.feature.detail.impl.domain.repository

interface ImageRepository {

    fun savePicture(
        url: String,
        saveName: String,
    )

    fun sharePicture(url: String)
}