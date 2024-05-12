package com.maxkor.feature.detail.impl.domain.interactor

import com.maxkor.feature.detail.impl.domain.model.CoinReminder
import com.maxkor.feature.detail.impl.domain.model.DownloadableImage
import com.maxkor.feature.detail.impl.domain.model.ExtraDetailCoinInfo

interface DetailInteractor {

    fun savePicture(
        downloadableImage: DownloadableImage,
        noPostNotificationPermissionCase: () -> Unit,
        noWriteStoragePermissionCase: () -> Unit,
        onDownloadState: (message: String) -> Unit,
    )

    fun sharePicture(url: String)

    fun createReminder(
        coinReminder: CoinReminder,
        noPostNotificationPermissionCase: () -> Unit,
        onIncorrectTimeInput: (String) -> Unit,
    )

    fun saveCoinExtraInfo(
        key: String,
        extraInfo: ExtraDetailCoinInfo,
    )

    fun getCoinExtraInfo(key: String): ExtraDetailCoinInfo
}