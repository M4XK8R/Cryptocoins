package com.maxkor.feature.detail.impl.domain.repository

interface ImageRepository {

    fun saveImage(
        url: String,
        saveName: String,
    )

    fun shareImage(url: String)
}