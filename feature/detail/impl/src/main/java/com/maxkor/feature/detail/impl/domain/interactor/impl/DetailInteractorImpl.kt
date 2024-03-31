package com.maxkor.feature.detail.impl.domain.interactor.impl

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
    ) = imageRepository.saveImage(
        url = url,
        saveName = saveName
    )

    override fun shareImage(url: String) =
        imageRepository.shareImage(url)

    override fun createReminder() {
        remainderRepository.showNotification("Eeeee", null)
    }

}