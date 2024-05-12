package com.maxkor.feature.favorites.impl.presentation.viewmodel

import com.maxkor.core.base.presentation.contract.CryptocoinsUiEvents
import com.maxkor.core.base.presentation.viewmodel.BaseViewModel
import com.maxkor.feature.coins.api.domain.interactor.CoinsFavoritesInteractor
import com.maxkor.feature.favorites.impl.presentation.contract.FavoritesEvents
import com.maxkor.feature.favorites.impl.presentation.mapper.toCryptocoin
import com.maxkor.feature.favorites.impl.presentation.mapper.toFavoriteCoinVo
import com.maxkor.feature.favorites.impl.presentation.screen.FavoritesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val interactor: CoinsFavoritesInteractor,
) : BaseViewModel() {

    val favoritesUiState: StateFlow<FavoritesUiState> = interactor
        .getFavoriteCoinsFlow()
        .map { favoriteCoins ->
            if (favoriteCoins.isNotEmpty()) {
                val favoritesCoinsVos = favoriteCoins.map { it.toFavoriteCoinVo() }
                FavoritesUiState.Success(coins = favoritesCoinsVos)
            } else {
                FavoritesUiState.NoDataAvailable
            }
        }
        .stateIn(
            scope = this,
            started = SharingStarted.WhileSubscribed(),
            initialValue = FavoritesUiState.Loading
        )

    private val _uiEvent = Channel<CryptocoinsUiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: FavoritesEvents) {
        when (event) {
            is FavoritesEvents.OnFavoriteCardClick ->
                sendNavigateUiEvent(event.coinName)

            is FavoritesEvents.OnFavoriteIconClick -> launch {
                interactor.removeFromFavorites(
                    event.favoriteCoinVo.toCryptocoin()
                )
            }
        }
    }

    /**
     * Private sector
     */
    private fun sendNavigateUiEvent(coinName: String) = launch {
        _uiEvent.send(
            CryptocoinsUiEvents.Navigate(coinName)
        )
    }
}