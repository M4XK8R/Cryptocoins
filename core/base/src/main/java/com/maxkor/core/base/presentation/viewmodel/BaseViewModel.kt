package com.maxkor.core.base.presentation.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.maxkor.core.base.util.createDebugLog
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    private val scopeJob: Job = SupervisorJob()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    override val coroutineContext: CoroutineContext =
        scopeJob + Dispatchers.Main + errorHandler

    override fun onCleared() {
        coroutineContext.cancelChildren()
        super.onCleared()
    }

    @CallSuper
    protected open fun handleError(throwable: Throwable) =
        createDebugLog(
            "CoroutineExceptionHandler: ${throwable.message}"
        )
}