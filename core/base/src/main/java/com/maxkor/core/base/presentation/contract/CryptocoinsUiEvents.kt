package com.maxkor.core.base.presentation.contract

sealed class CryptocoinsUiEvents {
    data class Navigate(val coinName: String) : CryptocoinsUiEvents()
    data class ShowSnackbar(val message: String) : CryptocoinsUiEvents()
}
