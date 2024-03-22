package com.maxkor.feature.coins.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.maxkor.feature.coins.impl.domain.interactor.CryptocoinsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class CoinsViewModel @Inject constructor(
    private val interactor: CryptocoinsInteractor,
) : ViewModel() {

}