package com.maxkor.feature.favorites.impl.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxkor.feature.favorites.impl.presentation.screen.FavoritesScreen
import com.maxkor.feature.favorites.impl.presentation.viewmodel.FavoritesViewModel


@Composable
fun FavoritesRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: FavoritesViewModel = hiltViewModel()
//    val someState by viewmodel.someStateFlow.collectAsStateWithLifecycle()

    FavoritesScreen(
        viewModel = viewModel,
        onBackClick = onBackClick,
        modifier = modifier
    )
}
