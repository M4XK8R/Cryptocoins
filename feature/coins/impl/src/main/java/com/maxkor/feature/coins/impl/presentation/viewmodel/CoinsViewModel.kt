package com.maxkor.feature.coins.impl.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxkor.core.base.util.createDebugLog
import com.maxkor.feature.coins.api.CoinsFeature
import com.maxkor.feature.coins.impl.domain.interactor.CoinsInteractor
import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.presentation.mapper.toCoinVo
import com.maxkor.feature.coins.impl.presentation.model.CoinVo
import com.maxkor.feature.coins.impl.presentation.screen.CoinsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val interactor: CoinsInteractor,
) : ViewModel() {

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
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = CoinsUiState.Loading
        )

    var searchedText by mutableStateOf("")
        private set

    fun findCoinByName(name: String) {
        searchedText = name
    }

    fun filterCoinsVos(coinsVos: List<CoinVo>): List<CoinVo> =
        coinsVos.filter { coinVo ->
            coinVo.name.lowercase()
                .startsWith(
                    searchedText.lowercase()
                )
        }

    fun informIfInternetIsNotAvailable(): String? =
        interactor.informIfInternetIsNotAvailable()

    fun changeCoinFavoriteState(coin: Coin) {
        viewModelScope.launch {
            interactor.changeCoinFavoriteState(coin)
        }
    }

    suspend fun updateData(
        informUserOnFailure: (String) -> Unit,
    ) = interactor.updateData(
        informUserOnFailure = informUserOnFailure
    )
}