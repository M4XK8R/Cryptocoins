package com.maxkor.feature.detail.impl.domain.interactor.impl

import com.maxkor.core.base.domain.repository.CheckerRepository
import com.maxkor.feature.detail.impl.domain.interactor.DetailInteractor
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

    override fun saveImage(
        url: String,
        saveName: String,
        noPostNotificationPermissionCase: () -> Unit,
        noWriteStoragePermissionCase: () -> Unit,
    ) = CheckerRepository.onPermissionStateAction(
        condition = checkerRepository.checkStoragePermission(),
        hasPermissionCase = {
            CheckerRepository.onPermissionStateAction(
                condition = checkerRepository.checkNotificationPermission(),
                hasPermissionCase = {
                    imageRepository.savePicture(
                        url = url,
                        saveName = saveName
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
        coinName: String,
        coinPrice: String,
        coinImageUrl: String,
        time: Long,
        noPostNotificationPermissionCase: () -> Unit,
    ) = CheckerRepository.onPermissionStateAction(
        condition = checkerRepository.checkNotificationPermission(),
        hasPermissionCase = {
            remainderRepository.createAlarm(
                coinName = coinName,
                coinPrice = coinPrice,
                coinImageUrl = coinImageUrl,
                time = time
            )
            remainderRepository.showNotification(
                contentText = "You will be notified about $coinName in ${(time / 1000.0).toInt()} seconds",
                contentIntent = null
            )
        },
        noPermissionCase = noPostNotificationPermissionCase,
    )

    override fun saveCoinExtraInfo(
        key: String,
        extraInfo: String,
    ) = detailPreferences.saveCoinExtraInfo(
        key = key,
        extraInfo = extraInfo
    )
}
