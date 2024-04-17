package com.maxkor.feature.favorites.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxkor.core.base.utils.createDebugLog
import com.maxkor.feature.coins.api.domain.interactor.CoinsFavoritesInteractor
import com.maxkor.feature.coins.api.domain.model.FavoriteCoin
import com.maxkor.feature.favorites.impl.presentation.mapper.toFavoriteCoinVo
import com.maxkor.feature.favorites.impl.presentation.screen.FavoritesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val interactor: CoinsFavoritesInteractor,
) : ViewModel() {

    val favoritesUiState: StateFlow<FavoritesUiState> = interactor
        .getFavoriteCoinsFlow()
        .onStart {}
        .onEach { createDebugLog("onEach") }
        .map { favoriteCoins ->
            if (favoriteCoins.isNotEmpty()) {
                val favoritesCoinsVos = favoriteCoins.map { it.toFavoriteCoinVo() }
                FavoritesUiState.Success(coins = favoritesCoinsVos)
            } else {
                FavoritesUiState.NoDataAvailable
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = FavoritesUiState.Loading
        )

    fun removeFromFavorites(favoriteCoin: FavoriteCoin) =
        viewModelScope.launch {
            interactor.removeFromFavorites(favoriteCoin)
        }
}