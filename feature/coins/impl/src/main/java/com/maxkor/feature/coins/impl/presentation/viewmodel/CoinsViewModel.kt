package com.maxkor.feature.coins.impl.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.maxkor.core.base.presentation.contract.CryptocoinsUiEvents
import com.maxkor.core.base.presentation.viewmodel.BaseViewModel
import com.maxkor.core.base.util.createDebugLog
import com.maxkor.feature.coins.api.CoinsFeature
import com.maxkor.feature.coins.impl.domain.interactor.CoinsInteractor
import com.maxkor.feature.coins.impl.presentation.contract.CoinsEvents
import com.maxkor.feature.coins.impl.presentation.mapper.toCoin
import com.maxkor.feature.coins.impl.presentation.mapper.toCoinVo
import com.maxkor.feature.coins.impl.presentation.model.CoinVo
import com.maxkor.feature.coins.impl.presentation.screen.CoinsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val interactor: CoinsInteractor,
) : BaseViewModel() {

    val coinsUiState: StateFlow<CoinsUiState> = interactor
        .getCoinsFlow()
        .onStart { delay(CoinsFeature.LOADING_DATA_TIME) }
        .onEach { createDebugLog("onEach") }
        .map { coins ->
            if (coins.isNotEmpty()) {
                val coinsVos = coins.map { it.toCoinVo() }
                CoinsUiState.Success(coinsVos = coinsVos)
            } else {
                CoinsUiState.NoDataAvailable
            }
        }
        .stateIn(
            scope = this,
            started = SharingStarted.WhileSubscribed(),
            initialValue = CoinsUiState.Loading
        )

    var searchedText by mutableStateOf("")
        private set

    private val _uiEvent = Channel<CryptocoinsUiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var coinsLoaderJob: Job? = null
    private var shouldLoadCoins: Boolean = false

    fun onEvent(event: CoinsEvents) {
        when (event) {
            is CoinsEvents.OnFavoriteIconClick -> changeCoinFavoriteState(event.coinVo)
            is CoinsEvents.OnCoinCardClick -> sendNavigateUiEvent(event.coinId)
            is CoinsEvents.OnSearch -> findCoinByName(event.query)
            is CoinsEvents.OnStartUpdatingCoins -> startUpdatingCoins(event.downtime)
            is CoinsEvents.OnStopUpdatingCoins -> stopUpdatingCoins()
        }
    }

    /**
     * Private sector
     */
    private fun findCoinByName(name: String) {
        searchedText = name
    }

    private fun startUpdatingCoins(downtime: Long) {
        createDebugLog("startUpdatingCoins")
        shouldLoadCoins = true
        coinsLoaderJob = updateCoins(downtime)
    }

    private fun stopUpdatingCoins() {
        createDebugLog("stopUpdatingCoins")
        shouldLoadCoins = false
        coinsLoaderJob?.cancel()
    }

    private fun updateCoins(downtime: Long) = launch {
        while (shouldLoadCoins) {
            interactor.updateCoins(
                informUserOnFailure = ::sendShowSnackbarUiEvent
            )
            delay(downtime)
        }
    }

    private fun changeCoinFavoriteState(coinVo: CoinVo) = launch {
        interactor.changeCoinFavoriteState(
            coin = coinVo.toCoin()
        )
    }

    private fun sendNavigateUiEvent(coinName: String) = launch {
        _uiEvent.send(
            CryptocoinsUiEvents.Navigate(coinName)
        )
    }

    private fun sendShowSnackbarUiEvent(message: String) = launch {
        _uiEvent.send(
            CryptocoinsUiEvents.ShowSnackbar(message)
        )
    }
}