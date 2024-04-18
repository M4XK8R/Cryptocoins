package com.maxkor.feature.detail.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxkor.feature.detail.impl.domain.interactor.DetailInteractor
import com.maxkor.feature.detail.impl.presentation.screen.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val interactor: DetailInteractor,
) : ViewModel() {

    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.ModeRead)
    val detailUiState = _detailUiState.asStateFlow()

    fun swapMode() {
        when (detailUiState.value) {
            DetailUiState.ModeEdit -> setModeRead()
            DetailUiState.ModeRead -> setModeEdit()
        }
    }

    fun setModeEdit() {
        viewModelScope.launch {
            _detailUiState.emit(DetailUiState.ModeEdit)
        }
    }

    fun setModeRead() {
        viewModelScope.launch {
            _detailUiState.emit(DetailUiState.ModeRead)
        }
    }

    fun createReminder(
        coinName: String,
        coinPrice: String,
        coinImageUrl: String,
        hour: Int,
        minute: Int,
        noPostNotificationPermissionCase: () -> Unit,
        onIncorrectTimeInput: (String) -> Unit,
    ) = interactor.createReminder(
        coinName = coinName,
        coinPrice = coinPrice,
        coinImageUrl = coinImageUrl,
        hour = hour,
        minute = minute,
        noPostNotificationPermissionCase = noPostNotificationPermissionCase,
        onIncorrectTimeInput = onIncorrectTimeInput
    )

    fun savePicture(
        url: String,
        saveName: String,
        noWriteStoragePermissionCase: () -> Unit,
        noPostNotificationPermissionCase: () -> Unit,
        onDownloadState: (message: String) -> Unit,
    ) = interactor.saveImage(
        url = url,
        saveName = saveName,
        noWriteStoragePermissionCase = noWriteStoragePermissionCase,
        noPostNotificationPermissionCase = noPostNotificationPermissionCase,
        onDownloadState = onDownloadState
    )

    fun sharePicture(url: String) =
        interactor.sharePicture(url)

    fun saveCoinExtraInfo(
        key: String,
        extraInfo: String,
    ) = interactor.saveCoinExtraInfo(
        key = key,
        extraInfo = extraInfo
    )

    fun onPermissionGranted(
        action: () -> Unit,
        informUserAboutAction: (delayTime: String) -> Unit,
        delayTime: Long = 5000L,
    ) {
        viewModelScope.launch {
            informUserAboutAction(
                millisecondsToSeconds(delayTime)
            )
            delay(delayTime)
            action()
        }
    }

    /**
     * Private sector
     */
    private fun millisecondsToSeconds(milliseconds: Long): String =
        (milliseconds / 1000).toDouble()
            .roundToInt()
            .toString()
}