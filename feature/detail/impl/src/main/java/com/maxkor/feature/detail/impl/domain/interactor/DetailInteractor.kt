package com.maxkor.feature.detail.impl.domain.interactor

interface DetailInteractor {

    fun saveImage(
        url: String,
        saveName: String,
        noPostNotificationPermissionCase: () -> Unit,
        noWriteStoragePermissionCase: () -> Unit,
    )

    fun sharePicture(url: String)

    fun createReminder(
        coinName: String,
        coinPrice: String,
        coinImageUrl: String,
        time: Long,
        noPostNotificationPermissionCase: () -> Unit,
    )

    fun saveCoinExtraInfo(
        key: String,
        extraInfo: String,
    )
}