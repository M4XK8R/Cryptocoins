package com.maxkor.feature.detail.impl.presentation.screen

import androidx.compose.runtime.Composable
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

    DetailScreen(
        detailCoinVo = detailCoinVo,
        savePicture = { url, name ->
            viewModel.savePicture(
                url = url,
                saveName = name
            )
        },
        sharePicture = { url ->
            viewModel.sharePicture(url)
        },
        createReminder = { viewModel.showNotification() },
        modifier = modifier
    )
}
