package com.maxkor.core.base.utils

import android.util.Log

private const val GLOBAL_TEST_DEFAULT_TAG = "global-test"

fun createDebugLog(
    message: String,
    tag: String = GLOBAL_TEST_DEFAULT_TAG,
) {
    Log.d(tag, message)
}