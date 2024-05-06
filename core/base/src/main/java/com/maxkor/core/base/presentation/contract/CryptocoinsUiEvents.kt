package com.maxkor.core.base.presentation.contract

import com.maxkor.core.base.presentation.model.CryptocoinVo

sealed class CryptocoinsUiEvents {
    data class Navigate(val cryptocoinVo: CryptocoinVo) : CryptocoinsUiEvents()
    data class ShowSnackbar(val message: String) : CryptocoinsUiEvents()
}
