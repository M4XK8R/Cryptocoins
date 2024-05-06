package com.maxkor.feature.coins.impl.presentation.contract

import com.maxkor.feature.coins.impl.presentation.model.CoinVo

sealed class CoinsEvents {
    data class OnFavoriteIconClick(val coinVo: CoinVo) : CoinsEvents()
    data class OnCoinCardClick(val coinVo: CoinVo) : CoinsEvents()
    data class OnSearch(val query: String) : CoinsEvents()
    data object OnInternetConnectionAbsent : CoinsEvents()
    data object OnGetCoinsRequest : CoinsEvents()
}