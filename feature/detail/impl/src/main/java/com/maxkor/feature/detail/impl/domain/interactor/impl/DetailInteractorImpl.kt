package com.maxkor.feature.detail.impl.domain.interactor.impl

import com.maxkor.core.base.domain.repository.CheckerRepository
import com.maxkor.feature.coins.api.domain.model.ExtraDetailCoinInfo
import com.maxkor.feature.detail.impl.domain.interactor.DetailInteractor
import com.maxkor.feature.detail.impl.domain.model.CoinReminder
import com.maxkor.feature.detail.impl.domain.model.DownloadableImage
import com.maxkor.feature.detail.impl.domain.preferences.DetailPreferences
import com.maxkor.feature.detail.impl.domain.repository.ImageRepository
import com.maxkor.feature.detail.impl.domain.repository.RemainderRepository
import javax.inject.Inject

class DetailInteractorImpl @Inject constructor(
    private val imageRepository: ImageRepository,
    private val remainderRepository: RemainderRepository,
    private val checkerRepository: CheckerRepository,
    private val detailPreferences: DetailPreferences,
) : DetailInteractor {

    override fun savePicture(
        downloadableImage: DownloadableImage,
        noPostNotificationPermissionCase: () -> Unit,
        noWriteStoragePermissionCase: () -> Unit,
        onDownloadState: (message: String) -> Unit,
    ) = CheckerRepository.onPermissionState(
        hasPermission = checkerRepository.hasStoragePermission(),
        hasPermissionCase = {
            CheckerRepository.onPermissionState(
                hasPermission = checkerRepository.hasNotificationPermission(),
                hasPermissionCase = {
                    imageRepository.savePicture(
                        downloadableImage = downloadableImage,
                        onDownloadState = onDownloadState,
                    )
                },
                noPermissionCase = noPostNotificationPermissionCase
            )
        },
        noPermissionCase = noWriteStoragePermissionCase
    )

    override fun sharePicture(url: String) =
        imageRepository.sharePicture(url)

    override fun createReminder(
        coinReminder: CoinReminder,
        noPostNotificationPermissionCase: () -> Unit,
        onIncorrectTimeInput: (String) -> Unit,
    ) = CheckerRepository.onPermissionState(
        hasPermission = checkerRepository.hasNotificationPermission(),
        hasPermissionCase = {
            remainderRepository.createReminder(
                coinReminder = coinReminder,
                onIncorrectTimeInput = onIncorrectTimeInput
            )
        },
        noPermissionCase = noPostNotificationPermissionCase,
    )

    override fun saveCoinExtraInfo(
        key: String,
        extraInfo: ExtraDetailCoinInfo,
    ) = detailPreferences.saveCoinExtraInfo(
        key = key,
        extraInfo = extraInfo.value
    )

    override fun getCoinExtraInfo(key: String): ExtraDetailCoinInfo =
        detailPreferences.getExtraCoinInfo(key)
}
