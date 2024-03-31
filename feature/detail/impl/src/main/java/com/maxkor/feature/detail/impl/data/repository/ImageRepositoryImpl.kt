package com.maxkor.feature.detail.impl.data.repository

import android.app.DownloadManager
import android.app.DownloadManager.Request.NETWORK_MOBILE
import android.app.DownloadManager.Request.NETWORK_WIFI
import android.app.DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.content.Intent.EXTRA_STREAM
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import com.maxkor.feature.detail.impl.domain.repository.ImageRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import org.jetbrains.annotations.NotNull
import java.io.File
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : ImageRepository {

    override fun saveImage(
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
        (context.getSystemService(
            Context.DOWNLOAD_SERVICE
        ) as DownloadManager?)?.enqueue(request)

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

    override fun shareImage(url: String) {
        val uri = Uri.parse(url)
        val shareIntent = Intent(ACTION_SEND).apply {
            type = "image/*"
            putExtra(EXTRA_STREAM, uri)
        }
        @NotNull val chooserIntent = Intent.createChooser(
            shareIntent,
            "Share image"
        ).also { it.flags = FLAG_ACTIVITY_NEW_TASK }

        context.startActivity(chooserIntent)
    }
}

