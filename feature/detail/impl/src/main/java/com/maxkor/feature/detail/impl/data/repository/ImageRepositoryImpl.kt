package com.maxkor.feature.detail.impl.data.repository

import com.maxkor.core.base.domain.service.InternetCheckerService
import com.maxkor.feature.detail.impl.domain.service.DownloadImageService
import com.maxkor.feature.detail.impl.domain.service.ShareImageService
import com.maxkor.feature.detail.impl.domain.repository.ImageRepository
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val downloadImageService: DownloadImageService,
    private val shareImageService: ShareImageService,
    private val internetCheckerService: InternetCheckerService,
) : ImageRepository {

    override fun savePicture(
        url: String,
        saveName: String,
        onDownloadState: (message: String) -> Unit,
    ) = downloadImageService.savePicture(
        url = url,
        saveName = saveName,
        isNetworkAvailable = with(internetCheckerService) {
            isMobileInternetAvailable or isWifiAvailable
        },
        onDownloadState = onDownloadState
    )

    override fun sharePicture(url: String) =
        shareImageService.sharePicture(url)
}

