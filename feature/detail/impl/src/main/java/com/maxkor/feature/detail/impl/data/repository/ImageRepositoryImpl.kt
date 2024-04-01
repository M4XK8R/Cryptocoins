package com.maxkor.feature.detail.impl.data.repository

import android.content.Context
import com.maxkor.feature.detail.impl.domain.repository.ImageRepository
import com.maxkor.feature.detail.impl.domain.service.DownloadImageService
import com.maxkor.feature.detail.impl.domain.service.ShareImageService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
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

