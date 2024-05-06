package com.maxkor.feature.coins.impl.presentation.screen

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val DOWNTIME = 30_000L

@Composable
fun CoinsRoute(
    navigateToDetail: (
        name: String,
        price: String,
        imageUrl: String,
    ) -> Unit,
    informUser: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: CoinsViewModel = hiltViewModel()
    val coinsUiState by viewModel.coinsUiState.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()

    var shouldLoadNewData: Boolean
    var dataLoaderJob: Job? = null

    val startUpdatingData: () -> Unit = {
        shouldLoadNewData = true
        dataLoaderJob = coroutineScope.launch {
            while (shouldLoadNewData) {
                viewModel.onEvent(CoinsEvents.OnInternetConnectionAbsent)
                viewModel.onEvent(CoinsEvents.OnGetCoinsRequest)
                delay(DOWNTIME)
            }
        }
    }

    val stopUpdatingData: () -> Unit = {
        shouldLoadNewData = false
        dataLoaderJob?.cancel()
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is CryptocoinsUiEvents.ShowSnackbar -> informUser(event.message)
                is CryptocoinsUiEvents.Navigate -> {
                    with(event.cryptocoinVo) {
                        navigateToDetail(name, price, imageUrl)
                    }
                }
            }
        }
    }

    LifecycleEventObserver(
        lifecycleOwner = lifecycleOwner,
        onEvent = { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                startUpdatingData()
            }
            if (event == Lifecycle.Event.ON_PAUSE) {
                stopUpdatingData()
            }
        }
    )

    CoinsScreen(
        coinsUiState = coinsUiState,
        lazyListState = lazyListState,
        navigateToDetail = { coinVo ->
            viewModel.onEvent(
                CoinsEvents.OnCoinCardClick(coinVo)
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
