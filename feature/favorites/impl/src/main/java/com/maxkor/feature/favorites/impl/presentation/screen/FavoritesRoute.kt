package com.maxkor.feature.favorites.impl.presentation.screen

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxkor.core.base.presentation.contract.CryptocoinsUiEvents
import com.maxkor.feature.favorites.impl.presentation.contract.FavoritesEvents
import com.maxkor.feature.favorites.impl.presentation.viewmodel.FavoritesViewModel

@Composable
fun FavoritesRoute(
    onFavoriteCardClick: (coinId: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: FavoritesViewModel = hiltViewModel()
    val favoritesUiState by viewModel.favoritesUiState
        .collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is CryptocoinsUiEvents.Navigate ->
                    onFavoriteCardClick(event.coinName)

                else -> Unit
            }
        }
    }

    FavoritesScreen(
        favoritesUiState = favoritesUiState,
        lazyListState = lazyListState,
        onFavoriteCardClick = onFavoriteCardClick,
        removeFromFavorites = { favoriteCoinVo ->
            viewModel.onEvent(
                FavoritesEvents.OnFavoriteIconClick(favoriteCoinVo)
            )
        },
        modifier = modifier
    )
}