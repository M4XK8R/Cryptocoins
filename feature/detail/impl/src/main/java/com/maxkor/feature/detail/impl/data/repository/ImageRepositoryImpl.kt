package com.maxkor.feature.detail.impl.data.repository

import com.maxkor.feature.detail.impl.domain.service.DownloadImageService
import com.maxkor.feature.detail.impl.domain.service.ShareImageService
import com.maxkor.feature.detail.impl.domain.repository.ImageRepository
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val downloadImageService: DownloadImageService,
    private val shareImageService: ShareImageService,
) : ImageRepository {

    override fun savePicture(
        url: String,
        saveName: String,
    ) = downloadImageService.savePicture(
        url = url,
        saveName = saveName
    )

    override fun sharePicture(url: String) =
        shareImageService.sharePicture(url)
}

