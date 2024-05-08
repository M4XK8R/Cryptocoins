package com.maxkor.feature.detail.impl.data.service

import android.app.DownloadManager
import android.app.DownloadManager.Request.NETWORK_MOBILE
import android.app.DownloadManager.Request.NETWORK_WIFI
import android.app.DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.maxkor.feature.detail.impl.R
import com.maxkor.feature.detail.impl.domain.model.DownloadableImage
import com.maxkor.feature.detail.impl.domain.service.DownloadImageService
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

class DownloadImageServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val downloadManager: DownloadManager,
) : DownloadImageService {

    override fun savePicture(
       downloadableImage: DownloadableImage,
        isNetworkAvailable: Boolean,
        onDownloadState: (message: String) -> Unit,
    ) = if (isNetworkAvailable) {
        try {
            downloadManager.enqueue(
                createDownloadRequest(
                    downloadUri = Uri.parse(downloadableImage.url),
                    saveName = downloadableImage.name
                )
            )
            onDownloadState(context.getString(R.string.image_download_started))
        } catch (javaException: java.lang.Exception) {
            onDownloadState(
                context.getString(R.string.image_download_failed)
            )
        }
    } else {
        onDownloadState(
            context.getString(
                com.maxkor.core.base.R.string.no_internet_connection_warning
            )
        )
    }

    /**
     * Private sector
     */
    private fun createDownloadRequest(
        downloadUri: Uri,
        saveName: String,
    ): DownloadManager.Request = DownloadManager.Request(
        downloadUri
    ).apply {
        setAllowedNetworkTypes(NETWORK_WIFI or NETWORK_MOBILE)
        setAllowedOverRoaming(false)
        setTitle(saveName)
        setMimeType("image/jpeg")
        setNotificationVisibility(VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        setDestinationInExternalPublicDir(
            Environment.DIRECTORY_PICTURES,
            File.separator + saveName + ".jpg"
        )
    }
}
