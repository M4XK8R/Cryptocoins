package com.maxkor.feature.detail.impl.presentation.screen

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxkor.core.base.utils.createDebugLog
import com.maxkor.feature.detail.impl.presentation.model.DetailCoinVo
import com.maxkor.feature.detail.impl.presentation.viewmodel.DetailViewModel

@SuppressLint("InlinedApi")
@Composable
fun DetailRoute(
    detailCoinVo: DetailCoinVo,
    recreateApplication: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: DetailViewModel = hiltViewModel()
    val detailUiState by viewModel.detailUiState.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                recreateApplication()
            } else {
                // TODO snackbar message
            }
        }
    )

    val grantPostNotificationRequest: () -> Unit = {
        launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
    }
    val grantWriteStorageRequest: () -> Unit = {
        createDebugLog("grantWriteStorageRequest")
        launcher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    DetailScreen(
        detailCoinVo = detailCoinVo,
        detailUiState = detailUiState,
        savePicture = { url, name ->
            viewModel.savePicture(
                url = url,
                saveName = name,
                noWriteStoragePermissionCase = {
                    grantWriteStorageRequest()
                },
                noPostNotificationPermissionCase = {
                    grantPostNotificationRequest()
                }
            )
        },
        sharePicture = { url ->
            viewModel.sharePicture(url)
        },
        createReminder = { coinName, coinPrice, coinImageUrl, time ->
            viewModel.createReminder(
                coinName = coinName,
                coinPrice = coinPrice,
                coinImageUrl = coinImageUrl,
                time = time,
                noPostNotificationPermissionCase = {
                    grantPostNotificationRequest()
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
