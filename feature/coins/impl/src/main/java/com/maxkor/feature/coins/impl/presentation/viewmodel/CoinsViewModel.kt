package com.maxkor.feature.coins.impl.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxkor.feature.coins.impl.domain.interactor.CryptocoinInteractor
import com.maxkor.feature.coins.impl.domain.model.Cryptocoin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val interactor: CryptocoinInteractor,
) : ViewModel() {

    val coinsState: MutableState<List<Cryptocoin>> = mutableStateOf(emptyList())

    fun getCoins() {
        viewModelScope.launch {
            val coins = interactor.getCoins()
            Log.d("Global", "coins = $coins")
            coinsState.value = coins
        }
    }

}