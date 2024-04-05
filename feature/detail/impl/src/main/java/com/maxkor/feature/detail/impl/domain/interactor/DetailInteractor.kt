package com.maxkor.feature.detail.impl.domain.interactor

interface DetailInteractor {

    fun saveImage(
        url: String,
        saveName: String,
    )

    fun sharePicture(url: String)

    fun createReminder(
        coinName: String,
        coinPrice: String,
        coinImageUrl: String,
        time: Long,
    )

    fun saveCoinExtraInfo(
        key: String,
        extraInfo: String,
    )
}