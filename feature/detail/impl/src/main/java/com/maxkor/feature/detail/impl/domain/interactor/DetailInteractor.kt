package com.maxkor.feature.detail.impl.domain.interactor

interface DetailInteractor {

    fun saveImage(
        url: String,
        saveName: String,
        noPostNotificationPermissionCase: () -> Unit,
        noWriteStoragePermissionCase: () -> Unit,
        onDownloadState: (message: String) -> Unit,
    )

    fun sharePicture(url: String)

    fun createReminder(
        coinName: String,
        coinPrice: String,
        coinImageUrl: String,
        hour: Int,
        minute: Int,
        noPostNotificationPermissionCase: () -> Unit,
        onIncorrectTimeInput: (String) -> Unit,
    )

    fun saveCoinExtraInfo(
        key: String,
        extraInfo: String,
    )
}