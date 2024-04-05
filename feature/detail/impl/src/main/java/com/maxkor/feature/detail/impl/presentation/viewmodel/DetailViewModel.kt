package com.maxkor.feature.detail.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxkor.feature.detail.impl.domain.interactor.DetailInteractor
import com.maxkor.feature.detail.impl.presentation.screen.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val interactor: DetailInteractor,
) : ViewModel() {

    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.ModeRead)
    val detailUiState = _detailUiState.asStateFlow()

    fun swapMode() {
        when (detailUiState.value) {
            DetailUiState.ModeEdit -> setModeRead()
            DetailUiState.ModeRead -> setModeEdit()
        }
    }

    fun setModeEdit() {
        viewModelScope.launch {
            _detailUiState.emit(DetailUiState.ModeEdit)
        }
    }

    fun setModeRead() {
        viewModelScope.launch {
            _detailUiState.emit(DetailUiState.ModeRead)
        }
    }


    fun createReminder(
        coinName: String,
        coinPrice: String,
        coinImageUrl: String,
        time: Long,
    ) = interactor.createReminder(
        coinName = coinName,
        coinPrice = coinPrice,
        coinImageUrl = coinImageUrl,
        time = time
    )

    fun savePicture(
        url: String,
        saveName: String,
    ) = interactor.saveImage(
        url = url,
        saveName = saveName
    )

    fun sharePicture(url: String) =
        interactor.sharePicture(url)

    fun saveCoinExtraInfo(
        key: String,
        extraInfo: String,
    ) = interactor.saveCoinExtraInfo(
        key = key,
        extraInfo = extraInfo
    )
}