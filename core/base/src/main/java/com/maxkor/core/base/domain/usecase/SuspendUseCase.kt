package com.maxkor.core.base.domain.usecase

import com.maxkor.core.base.util.createDebugLog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class SuspendUseCase<in Params, out ReturnType>(
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(params: Params): Result<ReturnType> =
        withContext(coroutineDispatcher) {
            try {
                execute(params).let { Result.Success(it) }
            } catch (e: Exception) {
                createDebugLog("${e.message}")
                Result.Failure(e)
            }
        }

    protected abstract suspend fun execute(parameters: Params): ReturnType

    sealed class Result<out R> {
        data class Success<out T>(val value: T) : Result<T>()
        data class Failure(val exception: Throwable? = null) : Result<Nothing>()
    }
}