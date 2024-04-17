package com.maxkor.feature.detail.impl.data.service

import android.app.DownloadManager
import android.app.DownloadManager.Request.NETWORK_MOBILE
import android.app.DownloadManager.Request.NETWORK_WIFI
import android.app.DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import com.maxkor.feature.detail.impl.domain.service.DownloadImageService
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

class DownloadImageServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val downloadManager: DownloadManager,
) : DownloadImageService {

    override fun savePicture(
        url: String,
        saveName: String,
        isNetworkAvailable: Boolean,
        onDownloadState: (message: String) -> Unit,
    ) = if (isNetworkAvailable) {
        try {
            downloadManager.enqueue(
                createDownloadRequest(
                    downloadUri = Uri.parse(url),
                    saveName = saveName
                )
            )
            onDownloadState("Image download started")
        } catch (javaException: java.lang.Exception) {
            onDownloadState(
                "Image download failed with ${javaException.message}"
            )
        }
    } else {
        onDownloadState(
            "No internet connection"
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
