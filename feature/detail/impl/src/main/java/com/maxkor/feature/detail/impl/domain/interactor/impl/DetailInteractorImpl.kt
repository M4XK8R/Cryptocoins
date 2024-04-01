package com.maxkor.feature.detail.impl.domain.interactor.impl

import com.maxkor.core.base.utils.createDebugLog
import com.maxkor.feature.detail.impl.domain.interactor.DetailInteractor
import com.maxkor.feature.detail.impl.domain.repository.ImageRepository
import com.maxkor.feature.detail.impl.domain.repository.RemainderRepository
import javax.inject.Inject

class DetailInteractorImpl @Inject constructor(
    private val imageRepository: ImageRepository,
    private val remainderRepository: RemainderRepository,
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

    override fun createReminder() {
        createDebugLog("DetailInteractorImpl: createReminder")
        remainderRepository.createAlarm("Bitcoin", 3000L)
        remainderRepository.showNotification("createReminder", null)
    }
}