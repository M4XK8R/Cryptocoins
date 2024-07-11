package com.maxkor.core.base.domain.usecase

import com.maxkor.core.base.util.createDebugLog

abstract class UseCase<in Params, out ReturnType>() {

    operator fun invoke(params: Params): Result<ReturnType> =
        try {
            execute(params).let { Result.Success(it) }
        } catch (e: Exception) {
            createDebugLog("${e.message}")
            Result.Failure(e)
        }

    protected abstract fun execute(parameters: Params): ReturnType

    sealed class Result<out R> {
        data class Success<out T>(val value: T) : Result<T>()
        data class Failure(val exception: Throwable? = null) : Result<Nothing>()
    }
}