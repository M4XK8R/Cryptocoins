package com.maxkor.feature.coins.impl.presentation.contract

import com.maxkor.feature.coins.impl.presentation.model.CoinVo

sealed class CoinsEvents {
    data class OnFavoriteIconClick(val coinVo: CoinVo) : CoinsEvents()
    data class OnCoinCardClick(val coinId: String) : CoinsEvents()
    data class OnSearch(val query: String) : CoinsEvents()
    data class OnStartUpdatingCoins(val downtime: Long) : CoinsEvents()
    data object OnStopUpdatingCoins : CoinsEvents()
}