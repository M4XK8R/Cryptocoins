package com.maxkor.feature.mainactivity.impl.presentation.viewmodel

import androidx.compose.material3.SnackbarHostState
import com.maxkor.core.base.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : BaseViewModel() {

    val snackbarHostState: SnackbarHostState = SnackbarHostState()

    fun showSnackbarMessage(message: String) = launch {
        snackbarHostState.showSnackbar(message)
    }
}