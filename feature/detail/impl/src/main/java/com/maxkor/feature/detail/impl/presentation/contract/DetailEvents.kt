package com.maxkor.feature.detail.impl.presentation.contract

import androidx.activity.compose.ManagedActivityResultLauncher
import com.maxkor.feature.detail.impl.domain.model.CoinReminder
import com.maxkor.feature.detail.impl.domain.model.DownloadableImage
import com.maxkor.feature.detail.impl.presentation.screen.DetailUiState

sealed class DetailEvents {
    data class OnScreenOpening(val coinName: String) : DetailEvents()

    data class OnBellImageClick(
        val coinReminder: CoinReminder,
        val launcher: ManagedActivityResultLauncher<String, Boolean>,
    ) : DetailEvents()

    data class OnDownloadImageClick(
        val downloadableImage: DownloadableImage,
        val launcher: ManagedActivityResultLauncher<String, Boolean>,
    ) : DetailEvents()

    data class OnShareImageClick(val imageUrl: String) : DetailEvents()

    data class OnSaveButtonClick(
        val key: String,
        val extraInfo: String,
    ) : DetailEvents()

    data object OnMainBoxClick : DetailEvents()
}