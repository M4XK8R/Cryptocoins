package com.maxkor.feature.mainactivity.impl.presentation.viewmodel

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    val snackbarHostState: SnackbarHostState = SnackbarHostState()

    fun showSnackbarMessage(message: String) {
        viewModelScope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }
}