package com.maxkor.feature.coins.impl.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxkor.feature.coins.impl.domain.interactor.CryptocoinsInteractor
import com.maxkor.feature.coins.impl.domain.model.Cryptocoin
import com.maxkor.feature.coins.impl.presentation.mapper.toCryptocoinVo
import com.maxkor.feature.coins.impl.presentation.screen.CoinsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val interactor: CryptocoinsInteractor,
) : ViewModel() {

    var coinsUiState: CoinsUiState by mutableStateOf(CoinsUiState.Loading)
        private set

    init {
        createFakeData()
        observeState()
    }

    private fun observeState() {
        interactor.getCoinsFlow()
            .onStart { delay(4000) }
            .onEach { cryptocoins ->
                Log.d("CoinsViewModel", "onEach")
                if (cryptocoins.isNotEmpty()) {
                    val cryptocoinsVo = cryptocoins.map { it.toCryptocoinVo() }
                    coinsUiState = CoinsUiState.Success(coins = cryptocoinsVo)
                }
            }
            .launchIn(viewModelScope)
    }

    private fun createFakeData() {
        viewModelScope.launch {
            while (true) {
                delay(3000)
                val fakeData = List(23) {
                    Cryptocoin(id = it, "", "", "", false)
                }
                interactor.updateDatabaseData(fakeData)
//                interactor.addCoins(fakeData)
                delay(3000)
                Log.d("CoinsViewModel", "createFakeData")
            }
        }
    }

    fun changeCoinFavoriteState(cryptocoin: Cryptocoin) {
        viewModelScope.launch {
            interactor.changeCoinFavoriteState(cryptocoin)
        }
    }
}