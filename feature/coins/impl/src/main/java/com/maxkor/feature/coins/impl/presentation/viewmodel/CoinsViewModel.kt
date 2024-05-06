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
import com.maxkor.feature.coins.impl.presentation.mapper.toCryptocoinVo
import com.maxkor.feature.coins.impl.presentation.model.CoinVo
import com.maxkor.feature.coins.impl.presentation.screen.CoinsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _uiEvent = Channel<CryptocoinsUiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var searchedText by mutableStateOf("")
        private set

    fun onEvent(event: CoinsEvents) {
        when (event) {
            is CoinsEvents.OnFavoriteIconClick -> changeCoinFavoriteState(event.coinVo)
            is CoinsEvents.OnCoinCardClick -> sendNavigateUiEvent(event.coinVo)
            is CoinsEvents.OnSearch -> findCoinByName(name = event.query)
            is CoinsEvents.OnInternetConnectionAbsent -> informIfInternetIsNotAvailable()
            is CoinsEvents.OnGetCoinsRequest -> updateData()
        }
    }

    /**
     * Private sector
     */
    private  fun findCoinByName(name: String) {
        searchedText = name
    }

    private fun informIfInternetIsNotAvailable() {
        val message = interactor.informIfInternetIsNotAvailable()
        message?.let(::sendShowSnackbarUiEvent)
    }

    private fun updateData() = launch {
        interactor.updateData(
            informUserOnFailure = ::sendShowSnackbarUiEvent
        )
    }

    private fun changeCoinFavoriteState(coinVo: CoinVo) = launch {
        interactor.changeCoinFavoriteState(
            coin = coinVo.toCoin()
        )
    }

    private fun sendNavigateUiEvent(coinVo: CoinVo) = launch {
        _uiEvent.send(
            CryptocoinsUiEvents.Navigate(
                cryptocoinVo = coinVo.toCryptocoinVo()
            )
        )
    }

    private fun sendShowSnackbarUiEvent(message: String) = launch {
        _uiEvent.send(
            CryptocoinsUiEvents.ShowSnackbar(message)
        )
    }
}