package com.maxkor.feature.detail.impl.domain.interactor

interface DetailInteractor {

    fun saveImage(
        url: String,
        saveName: String,
    )

    fun shareImage(url: String)

    fun createReminder()
}