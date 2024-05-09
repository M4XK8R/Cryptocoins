package com.maxkor.feature.detail.impl.presentation.screen

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxkor.core.base.presentation.contract.CryptocoinsUiEvents
import com.maxkor.feature.detail.impl.R
import com.maxkor.feature.detail.impl.domain.model.CoinReminder
import com.maxkor.feature.detail.impl.domain.model.DownloadableImage
import com.maxkor.feature.detail.impl.domain.model.ExtraDetailCoinInfo
import com.maxkor.feature.detail.impl.presentation.contract.DetailEvents
import com.maxkor.feature.detail.impl.presentation.mapper.toDetailCoin
import com.maxkor.feature.detail.impl.presentation.viewmodel.DetailViewModel

@SuppressLint("InlinedApi")
@Composable
fun DetailRoute(
    coinName: String,
    coinExtraInfo: ExtraDetailCoinInfo,
    recreateApplication: () -> Unit,
    informUser: (message: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val viewModel: DetailViewModel = hiltViewModel()
    val detailUiState by viewModel.detailUiState
        .collectAsStateWithLifecycle()

    val detailCoinVo = when (detailUiState) {
        is DetailUiState.ModeEdit ->
            (detailUiState as DetailUiState.ModeEdit).detailCoinVo
        is DetailUiState.ModeRead ->
            (detailUiState as DetailUiState.ModeRead).detailCoinVo
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                viewModel.onPermissionGranted(
                    action = recreateApplication,
                    informUserAboutAction = { delayTime ->
                        informUser(
                            context.getString(
                                R.string.app_will_be_restarted,
                                delayTime
                            )
                        )
                    }
                )
            } else {
                informUser(
                    context.getString(
                        com.maxkor.core.base.R.string.no_permission_warning
                    )
                )
            }
        }
    )

    var coinExtraInfoInput by remember {
        mutableStateOf(coinExtraInfo.value)
    }
    val editCoinExtraInfoInput: (String) -> Unit = { inputText ->
        coinExtraInfoInput = inputText
    }

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(
            DetailEvents.OnScreenOpening(coinName)
        )
        viewModel.uiEvent.collect { event ->
            when (event) {
                is CryptocoinsUiEvents.ShowSnackbar ->
                    informUser(event.message)
                else -> Unit
            }
        }
    }

    DetailScreen(
        detailCoinVo = detailCoinVo,
        detailUiState = detailUiState,
        coinExtraInfoInput = coinExtraInfoInput,
        editCoinExtraInfoInput = editCoinExtraInfoInput,
        savePicture = {
            viewModel.onEvent(
                DetailEvents.OnDownloadImageClick(
                    downloadableImage = DownloadableImage(
                        url = detailCoinVo.imageUrl,
                        name = detailCoinVo.name
                    ),
                    launcher = launcher
                )
            )
        },
        sharePicture = {
            viewModel.onEvent(
                DetailEvents.OnShareImageClick(
                    detailCoinVo.imageUrl
                )
            )
        },
        createReminder = { hour, minute ->
            viewModel.onEvent(
                DetailEvents.OnBellImageClick(
                    coinReminder = CoinReminder(
                        detailCoin = detailCoinVo.toDetailCoin(),
                        hour = hour,
                        minute = minute,
                    ),
                    launcher = launcher
                )
            )
        },
        addCoinExtraInfo = {
            viewModel.onEvent(DetailEvents.OnMainBoxClick)
        },
        saveCoinExtraInfo = {
            viewModel.onEvent(
                DetailEvents.OnSaveButtonClick(
                    key = detailCoinVo.name,
                    extraInfo = coinExtraInfoInput
                )
            )
            viewModel.onEvent(DetailEvents.OnMainBoxClick)
        },
        onTextSectionClick = {
            viewModel.onEvent(DetailEvents.OnMainBoxClick)
        },
        modifier = modifier
    )
}
