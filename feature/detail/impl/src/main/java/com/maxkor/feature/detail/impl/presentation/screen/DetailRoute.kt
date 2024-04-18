package com.maxkor.feature.detail.impl.presentation.screen

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxkor.feature.detail.impl.R
import com.maxkor.feature.detail.impl.presentation.model.DetailCoinVo
import com.maxkor.feature.detail.impl.presentation.viewmodel.DetailViewModel

@SuppressLint("InlinedApi")
@Composable
fun DetailRoute(
    detailCoinVo: DetailCoinVo,
    recreateApplication: () -> Unit,
    informUser: (message: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    val viewModel: DetailViewModel = hiltViewModel()
    val detailUiState by viewModel.detailUiState.collectAsStateWithLifecycle()

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

    val grantPostNotificationRequest: () -> Unit = {
        launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
    }
    val grantWriteStorageRequest: () -> Unit = {
        launcher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    var coinExtraInfoInput by remember {
        mutableStateOf(detailCoinVo.extraInfo.value)
    }
    val editCoinExtraInfoInput: (String) -> Unit = { inputText ->
        coinExtraInfoInput = inputText
    }

    DetailScreen(
        detailCoinVo = detailCoinVo,
        detailUiState = detailUiState,
        coinExtraInfoInput = coinExtraInfoInput,
        editCoinExtraInfoInput = editCoinExtraInfoInput,
        savePicture = { url, name ->
            viewModel.savePicture(
                url = url,
                saveName = name,
                noWriteStoragePermissionCase = {
                    grantWriteStorageRequest()
                },
                noPostNotificationPermissionCase = {
                    grantPostNotificationRequest()
                },
                onDownloadState = informUser
            )
        },
        sharePicture = { url ->
            viewModel.sharePicture(url)
        },
        createReminder = { coinName, coinPrice, coinImageUrl, hour, minute ->
            viewModel.createReminder(
                coinName = coinName,
                coinPrice = coinPrice,
                coinImageUrl = coinImageUrl,
                hour = hour,
                minute = minute,
                noPostNotificationPermissionCase = {
                    grantPostNotificationRequest()
                },
                onIncorrectTimeInput = { message ->
                    informUser(message)
                }
            )
        },
        addCoinExtraInfo = {
            viewModel.setModeEdit()
        },
        saveCoinExtraInfo = { key, extraInfo ->
            viewModel.saveCoinExtraInfo(
                key = key,
                extraInfo = extraInfo
            )
            viewModel.setModeRead()
        },
        onTextSectionClick = { viewModel.swapMode() },
        modifier = modifier
    )
}
