package com.maxkor.feature.detail.impl.domain.usecase

import com.maxkor.core.base.domain.repository.CheckerRepository
import com.maxkor.core.base.domain.usecase.UseCase
import com.maxkor.feature.detail.impl.domain.model.parameters.SavePictureParams
import com.maxkor.feature.detail.impl.domain.repository.ImageRepository
import javax.inject.Inject

class SavePictureUseCase @Inject constructor(
    private val imageRepository: ImageRepository,
    private val checkerRepository: CheckerRepository,
) : UseCase<SavePictureParams, Unit>() {

    override fun execute(parameters: SavePictureParams) =
        CheckerRepository.onPermissionState(
            hasPermission = checkerRepository.hasStoragePermission(),
            hasPermissionCase = {
                CheckerRepository.onPermissionState(
                    hasPermission = checkerRepository.hasNotificationPermission(),
                    hasPermissionCase = {
                        imageRepository.savePicture(
                            downloadableImage = parameters.downloadableImage,
                            onDownloadState = parameters.onDownloadState,
                        )
                    },
                    noPermissionCase = parameters.noPostNotificationPermissionCase
                )
            },
            noPermissionCase = parameters.noWriteStoragePermissionCase
        )
}