package com.maxkor.feature.detail.impl.presentation.screen

sealed class DetailUiState {
    data object ModeRead : DetailUiState()
    data object ModeEdit : DetailUiState()
}