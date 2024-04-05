package com.maxkor.feature.detail.impl.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxkor.feature.detail.impl.presentation.model.DetailCoinVo
import com.maxkor.feature.detail.impl.presentation.viewmodel.DetailViewModel

@Composable
fun DetailRoute(
    detailCoinVo: DetailCoinVo,
    modifier: Modifier = Modifier,
) {
    val viewModel: DetailViewModel = hiltViewModel()
    val detailUiState by viewModel.detailUiState.collectAsState()

    DetailScreen(
        detailCoinVo = detailCoinVo,
        detailUiState = detailUiState,
        savePicture = { url, name ->
            viewModel.savePicture(
                url = url,
                saveName = name
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
                time = time
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
