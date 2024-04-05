package com.maxkor.feature.detail.impl.domain.interactor.impl

import com.maxkor.feature.detail.impl.domain.interactor.DetailInteractor
import com.maxkor.feature.detail.impl.domain.preferences.DetailPreferences
import com.maxkor.feature.detail.impl.domain.repository.ImageRepository
import com.maxkor.feature.detail.impl.domain.repository.RemainderRepository
import javax.inject.Inject

class DetailInteractorImpl @Inject constructor(
    private val imageRepository: ImageRepository,
    private val remainderRepository: RemainderRepository,
    private val detailPreferences: DetailPreferences,
) : DetailInteractor {

    override fun saveImage(
        url: String,
        saveName: String,
    ) = imageRepository.savePicture(
        url = url,
        saveName = saveName
    )

    override fun sharePicture(url: String) =
        imageRepository.sharePicture(url)

    override fun createReminder(
        coinName: String,
        coinPrice: String,
        coinImageUrl: String,
        time: Long,
    ) {
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
    }

    override fun saveCoinExtraInfo(
        key: String,
        extraInfo: String,
    ) = detailPreferences.saveCoinExtraInfo(
        key = key,
        extraInfo = extraInfo
    )
}