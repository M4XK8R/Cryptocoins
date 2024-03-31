package com.maxkor.feature.detail.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.maxkor.feature.detail.impl.domain.interactor.DetailInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val interactor: DetailInteractor,
) : ViewModel() {

    fun showNotification() =
        interactor.createReminder()

    fun savePicture(
        url: String,
        saveName: String,
    ) = interactor.saveImage(
        url = url,
        saveName = saveName
    )

    fun sharePicture(url: String) =
        interactor.shareImage(url)
}