package com.maxkor.feature.coins.impl.presentation.screen

import com.maxkor.feature.coins.impl.presentation.model.CoinVo

sealed interface CoinsUiState {
    data object Loading : CoinsUiState
    data object NoDataAvailable : CoinsUiState
    data class Success(val coins: List<CoinVo>) : CoinsUiState
}
