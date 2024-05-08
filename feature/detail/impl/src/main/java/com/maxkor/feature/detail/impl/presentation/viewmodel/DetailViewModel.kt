package com.maxkor.feature.detail.impl.presentation.viewmodel

import android.Manifest
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.annotation.RequiresApi
import com.maxkor.core.base.presentation.contract.CryptocoinsUiEvents
import com.maxkor.core.base.presentation.viewmodel.BaseViewModel
import com.maxkor.feature.detail.impl.domain.interactor.DetailInteractor
import com.maxkor.feature.detail.impl.presentation.contract.DetailEvents
import com.maxkor.feature.detail.impl.presentation.screen.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val interactor: DetailInteractor,
) : BaseViewModel() {

    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.ModeRead)
    val detailUiState = _detailUiState.asStateFlow()

    private val _uiEvent = Channel<CryptocoinsUiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun onEvent(event: DetailEvents) {
        when (event) {
            is DetailEvents.OnBellImageClick -> interactor.createReminder(
                coinReminder = event.coinReminder,
                noPostNotificationPermissionCase = {
                    grantPermission(
                        permission = Manifest.permission.POST_NOTIFICATIONS,
                        launcher = event.launcher
                    )
                },
                onIncorrectTimeInput = ::sendShowSnackbarUiEvent
            )

            is DetailEvents.OnDownloadImageClick -> interactor.savePicture(
                downloadableImage = event.downloadableImage,
                noWriteStoragePermissionCase = {
                    grantPermission(
                        permission = Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        launcher = event.launcher
                    )
                },
                noPostNotificationPermissionCase = {
                    grantPermission(
                        permission = Manifest.permission.POST_NOTIFICATIONS,
                        launcher = event.launcher
                    )
                },
                onDownloadState = ::sendShowSnackbarUiEvent
            )

            is DetailEvents.OnShareImageClick -> interactor.sharePicture(event.imageUrl)

            is DetailEvents.OnMainBoxClick -> setScreenMode(event.mode)

            is DetailEvents.OnSaveButtonClick -> interactor.saveCoinExtraInfo(
                key = event.key,
                extraInfo = event.extraInfo
            )
        }
    }

    fun onPermissionGranted(
        action: () -> Unit,
        informUserAboutAction: (delayTime: String) -> Unit,
        delayTime: Long = 5000L,
    ) = launch {
        informUserAboutAction(millisecondsToSeconds(delayTime))
        delay(delayTime)
        action()
    }

    /**
     * Private sector
     */
    private fun setScreenMode(mode: DetailUiState) = launch {
        _detailUiState.emit(mode)
    }

    private fun grantPermission(
        permission: String,
        launcher: ManagedActivityResultLauncher<String, Boolean>,
    ) = launcher.launch(permission)

    private fun sendShowSnackbarUiEvent(message: String) = launch {
        _uiEvent.send(
            CryptocoinsUiEvents.ShowSnackbar(message)
        )
    }

    private fun millisecondsToSeconds(milliseconds: Long): String =
        (milliseconds / 1000).toDouble()
            .roundToInt()
            .toString()
}