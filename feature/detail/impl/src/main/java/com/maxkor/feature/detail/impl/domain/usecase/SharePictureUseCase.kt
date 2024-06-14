package com.maxkor.feature.detail.impl.domain.usecase

import com.maxkor.core.base.domain.usecase.UseCase
import com.maxkor.feature.detail.impl.domain.model.parameters.SharePictureParams
import com.maxkor.feature.detail.impl.domain.repository.ImageRepository
import javax.inject.Inject

class SharePictureUseCase @Inject constructor(
    private val imageRepository: ImageRepository,
) : UseCase<SharePictureParams, Unit>() {

    override fun execute(parameters: SharePictureParams) =
        imageRepository.sharePicture(parameters.imageUrl)
}