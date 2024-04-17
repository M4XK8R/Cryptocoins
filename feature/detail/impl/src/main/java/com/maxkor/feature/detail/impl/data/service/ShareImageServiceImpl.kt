package com.maxkor.feature.detail.impl.data.service

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.maxkor.feature.detail.impl.domain.service.ShareImageService
import dagger.hilt.android.qualifiers.ApplicationContext
import org.jetbrains.annotations.NotNull
import javax.inject.Inject

class ShareImageServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : ShareImageService {

    override fun sharePicture(url: String) =
        context.startActivity(
            createChooserIntent(
                shareIntent = createShareIntent(
                    uri = Uri.parse(url)
                )
            )
        )

    /**
     * Private sector
     */
    private fun createChooserIntent(shareIntent: Intent): Intent =
        Intent.createChooser(
            shareIntent,
            "Share image"
        ).also { it.flags = Intent.FLAG_ACTIVITY_NEW_TASK }

    private fun createShareIntent(uri: Uri): Intent =
        Intent(Intent.ACTION_SEND).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_STREAM, uri)
        }
}