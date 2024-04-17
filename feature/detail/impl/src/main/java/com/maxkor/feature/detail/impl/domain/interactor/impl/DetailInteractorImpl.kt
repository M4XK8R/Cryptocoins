package com.maxkor.feature.detail.impl.domain.interactor.impl

import com.maxkor.core.base.domain.repository.CheckerRepository
import com.maxkor.feature.detail.impl.domain.interactor.DetailInteractor
import com.maxkor.feature.detail.impl.domain.preferences.DetailPreferences
import com.maxkor.feature.detail.impl.domain.repository.ImageRepository
import com.maxkor.feature.detail.impl.domain.repository.RemainderRepository
import com.maxkor.feature.detail.impl.domain.utils.getCalendar
import com.maxkor.feature.detail.impl.domain.utils.getCalendarTime
import com.maxkor.feature.detail.impl.domain.utils.setUpCalendar
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
        onDownloadState: (message: String) -> Unit,
    ) = CheckerRepository.onPermissionStateAction(
        condition = checkerRepository.checkStoragePermission(),
        hasPermissionCase = {
            CheckerRepository.onPermissionStateAction(
                condition = checkerRepository.checkNotificationPermission(),
                hasPermissionCase = {
                    imageRepository.savePicture(
                        url = url,
                        saveName = saveName,
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
        coinName: String,
        coinPrice: String,
        coinImageUrl: String,
        hour: Int,
        minute: Int,
        noPostNotificationPermissionCase: () -> Unit,
        onIncorrectTimeInput: (String) -> Unit,
    ) = CheckerRepository.onPermissionStateAction(
        condition = checkerRepository.checkNotificationPermission(),
        hasPermissionCase = {
            val calendar = getCalendar()
            val currentTime = calendar.getCalendarTime()
            calendar.setUpCalendar(
                hour = hour,
                minute = minute
            )
            val targetTime = calendar.getCalendarTime()
            if (targetTime <= currentTime) {
                onIncorrectTimeInput("This time has already passed")
            } else {
                remainderRepository.createAlarm(
                    coinName = coinName,
                    coinPrice = coinPrice,
                    coinImageUrl = coinImageUrl,
                    time = targetTime
                )
                remainderRepository.createAndShowNotification(
                    contentText = "You will be notified about $coinName at ${calendar.time}",
                    contentIntent = null
                )
            }
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
