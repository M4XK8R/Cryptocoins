package com.maxkor.feature.coins.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxkor.core.base.utils.createDebugLog
import com.maxkor.feature.coins.impl.domain.interactor.CryptocoinsInteractor
import com.maxkor.feature.coins.impl.domain.model.Cryptocoin
import com.maxkor.feature.coins.impl.presentation.mapper.toCryptocoinVo
import com.maxkor.feature.coins.impl.presentation.screen.CoinsUiState
import com.maxkor.feature.coins.impl.presentation.screen.CoinsUiState.Success
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
    private val interactor: CryptocoinsInteractor,
) : ViewModel() {

    init {
//        createFakeData()
//        addEmptyDataToDatabase()
    }

    val coinsUiState: StateFlow<CoinsUiState> = interactor
        .getCoinsFlow()
        .onStart { delay(4000) }
        .onEach { createDebugLog("onEach") }
        .map { cryptocoins ->
            if (cryptocoins.isNotEmpty()) {
                val cryptocoinsVos = cryptocoins.map { it.toCryptocoinVo() }
                Success(coins = cryptocoinsVos)
            } else {
                CoinsUiState.NoDataAvailable
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(1000),
            initialValue = CoinsUiState.Loading
        )

    fun changeCoinFavoriteState(cryptocoin: Cryptocoin) {
        viewModelScope.launch {
            interactor.changeCoinFavoriteState(cryptocoin)
        }
    }

    /**
     * Private functions
     */

    private fun addEmptyDataToDatabase() {
        viewModelScope.launch {
            while (true) {
                delay(10000)
                interactor.addCoins(emptyList())
            }
        }
    }

    private fun addFakeDataToDatabase() {
        viewModelScope.launch {
            while (true) {
                delay(10000)
                interactor.updateDatabaseData(Cryptocoin.testExemplars)
//                interactor.addCoins(Cryptocoin.testExemplars)
            }
        }
    }
}