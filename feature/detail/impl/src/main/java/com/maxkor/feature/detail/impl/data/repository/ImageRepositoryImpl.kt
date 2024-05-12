package com.maxkor.feature.detail.impl.data.repository

import com.maxkor.core.base.domain.service.InternetCheckerService
import com.maxkor.feature.detail.impl.domain.model.DownloadableImage
import com.maxkor.feature.detail.impl.domain.repository.ImageRepository
import com.maxkor.feature.detail.impl.domain.service.DownloadImageService
import com.maxkor.feature.detail.impl.domain.service.ShareImageService
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val downloadImageService: DownloadImageService,
    private val shareImageService: ShareImageService,
    private val internetCheckerService: InternetCheckerService,
) : ImageRepository {

    override fun savePicture(
        downloadableImage: DownloadableImage,
        onDownloadState: (message: String) -> Unit,
    ) = downloadImageService.savePicture(
        downloadableImage = downloadableImage,
        isNetworkAvailable = with(internetCheckerService) {
            isMobileInternetAvailable() or isWifiAvailable()
        },
        onDownloadState = onDownloadState
    )

    override fun sharePicture(url: String) =
        shareImageService.sharePicture(url)
}

