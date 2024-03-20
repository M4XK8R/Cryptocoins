package com.maxkor.feature.detail.impl.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxkor.feature.detail.impl.presentation.screen.DetailScreen
import com.maxkor.feature.detail.impl.presentation.viewmodel.DetailViewModel

@Composable
fun DetailRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: DetailViewModel = hiltViewModel()
//    val someState by viewmodel.someStateFlow.collectAsStateWithLifecycle()

    DetailScreen (
        viewModel = viewModel,
        onBackClick = onBackClick,
        modifier = modifier
    )
}
