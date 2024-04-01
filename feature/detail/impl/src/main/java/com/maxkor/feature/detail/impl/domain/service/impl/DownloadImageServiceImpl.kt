package com.maxkor.feature.detail.impl.domain.service.impl

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
) : DownloadImageService {

    override fun savePicture(
        url: String,
        saveName: String,
    ) = try {
        val downloadUri = Uri.parse(url)
        val request = DownloadManager.Request(downloadUri).apply {
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
        val downloadManager = context.getSystemService(
            Context.DOWNLOAD_SERVICE
        ) as DownloadManager?
        downloadManager?.enqueue(request)

        Toast.makeText(
            context,
            "Image download started.",
            Toast.LENGTH_SHORT
        ).show()

    } catch (javaException: java.lang.Exception) {
        Toast.makeText(
            context,
            "Image download failed.",
            Toast.LENGTH_SHORT
        ).show()
    }
}