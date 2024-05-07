package com.maxkor.feature.detail.impl.domain.interactor.impl

import android.content.Context
import com.maxkor.core.base.domain.repository.CheckerRepository
import com.maxkor.feature.detail.impl.R
import com.maxkor.feature.detail.impl.domain.interactor.DetailInteractor
import com.maxkor.feature.detail.impl.domain.preferences.DetailPreferences
import com.maxkor.feature.detail.impl.domain.repository.ImageRepository
import com.maxkor.feature.detail.impl.domain.repository.RemainderRepository
import com.maxkor.feature.detail.impl.domain.util.getCalendar
import com.maxkor.feature.detail.impl.domain.util.getCalendarTime
import com.maxkor.feature.detail.impl.domain.util.setUpCalendar
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DetailInteractorImpl @Inject constructor(
    @ApplicationContext private val context: Context,
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
    ) = CheckerRepository.onPermissionState(
        hasPermission = checkerRepository.hasStoragePermission(),
        hasPermissionCase = {
            CheckerRepository.onPermissionState(
                hasPermission = checkerRepository.hasNotificationPermission(),
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
    ) = CheckerRepository.onPermissionState(
        hasPermission = checkerRepository.hasNotificationPermission(),
        hasPermissionCase = {
            val calendar = getCalendar()
            val currentTime = calendar.getCalendarTime()
            calendar.setUpCalendar(
                hour = hour,
                minute = minute
            )
            val targetTime = calendar.getCalendarTime()
            if (targetTime <= currentTime) {
                onIncorrectTimeInput(
                    context.getString(
                        R.string.time_has_already_passed
                    )
                )
            } else {
                remainderRepository.createAlarm(
                    coinName = coinName,
                    coinPrice = coinPrice,
                    coinImageUrl = coinImageUrl,
                    time = targetTime
                )
                remainderRepository.createAndShowNotification(
                    contentText = context.getString(
                        R.string.remind_about_coin_message,
                        coinName,
                        calendar.time
                    ),
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
