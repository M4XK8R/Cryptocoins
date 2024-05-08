package com.maxkor.feature.coins.impl.presentation.screen

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxkor.core.base.presentation.contract.CryptocoinsUiEvents
import com.maxkor.feature.coins.impl.presentation.components.LifecycleEventObserver
import com.maxkor.feature.coins.impl.presentation.contract.CoinsEvents
import com.maxkor.feature.coins.impl.presentation.viewmodel.CoinsViewModel

private const val COINS_UPDATE_INTERVAL = 30_000L

@Composable
fun CoinsRoute(
    navigateToDetail: (coinName: String) -> Unit,
    informUser: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: CoinsViewModel = hiltViewModel()
    val coinsUiState by viewModel.coinsUiState.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is CryptocoinsUiEvents.ShowSnackbar -> informUser(event.message)
                is CryptocoinsUiEvents.Navigate -> { navigateToDetail(event.coinName) }
            }
        }
    }

    LifecycleEventObserver(
        lifecycleOwner = lifecycleOwner,
        onEvent = { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.onEvent(
                    CoinsEvents.OnStartUpdatingCoins(COINS_UPDATE_INTERVAL)
                )
            }
            if (event == Lifecycle.Event.ON_PAUSE) {
                viewModel.onEvent(
                    CoinsEvents.OnStopUpdatingCoins
                )
            }
        }
    )

    CoinsScreen(
        coinsUiState = coinsUiState,
        lazyListState = lazyListState,
        navigateToDetail = { coinName ->
            viewModel.onEvent(
                CoinsEvents.OnCoinCardClick(coinName)
            )
        },
        changeFavoriteState = { coinVo ->
            viewModel.onEvent(
                CoinsEvents.OnFavoriteIconClick(coinVo)
            )
        },
        searchedCoin = viewModel.searchedText,
        search = { coinName ->
            viewModel.onEvent(
                CoinsEvents.OnSearch(
                    query = coinName
                )
            )
        },
        modifier = modifier
    )
}
