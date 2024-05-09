package com.maxkor.feature.detail.impl.presentation.viewmodel

import android.Manifest
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.annotation.RequiresApi
import com.maxkor.core.base.presentation.contract.CryptocoinsUiEvents
import com.maxkor.core.base.presentation.viewmodel.BaseViewModel
import com.maxkor.feature.coins.api.domain.interactor.CoinsDetailInteractor
import com.maxkor.feature.detail.impl.domain.interactor.DetailInteractor
import com.maxkor.feature.detail.impl.domain.model.ExtraDetailCoinInfo
import com.maxkor.feature.detail.impl.presentation.contract.DetailEvents
import com.maxkor.feature.detail.impl.presentation.mapper.toDetailCoinVo
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
    private val coinsDetailInteractor: CoinsDetailInteractor,
) : BaseViewModel() {

    private val _detailUiState = MutableStateFlow<DetailUiState>(
        DetailUiState.ModeRead()
    )
    val detailUiState = _detailUiState.asStateFlow()

    private val _uiEvent = Channel<CryptocoinsUiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun onEvent(event: DetailEvents) {
        when (event) {
            is DetailEvents.OnScreenOpening -> setStateValue(event.coinName)

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

            is DetailEvents.OnSaveButtonClick -> interactor.saveCoinExtraInfo(
                key = event.key,
                extraInfo = ExtraDetailCoinInfo(event.extraInfo)
            )

            is DetailEvents.OnMainBoxClick -> swapScreenMode()
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
    private fun setStateValue(coinName: String) = launch {
        val detailCoinVo = coinsDetailInteractor
            .getCoinByName(coinName)
            .toDetailCoinVo()

        val newState = createDetailUiState(
            modeEditCase = { modeEditState ->
                modeEditState.copy(
                    detailCoinVo = detailCoinVo
                )
            },
            modeReadCase = { modeReadState ->
                modeReadState.copy(
                    detailCoinVo = detailCoinVo
                )
            }
        )
        emitState(newState)
    }

    private fun swapScreenMode() {
        val newState = createDetailUiState(
            modeEditCase = { modeEditState ->
                DetailUiState.ModeRead(
                    detailCoinVo = modeEditState.detailCoinVo
                )
            },
            modeReadCase = { modeReadState ->
                DetailUiState.ModeEdit(
                    detailCoinVo = modeReadState.detailCoinVo
                )
            }
        )
        emitState(newState)
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

    // Utils
    private fun emitState(state: DetailUiState) = launch {
        _detailUiState.emit(state)
    }

    private fun createDetailUiState(
        modeEditCase: (DetailUiState.ModeEdit) -> DetailUiState,
        modeReadCase: (DetailUiState.ModeRead) -> DetailUiState,
    ): DetailUiState = when (val state = _detailUiState.value) {
        is DetailUiState.ModeEdit -> modeEditCase(state)
        is DetailUiState.ModeRead -> modeReadCase(state)
    }

    private fun millisecondsToSeconds(milliseconds: Long): String =
        (milliseconds / 1000).toDouble()
            .roundToInt()
            .toString()
}