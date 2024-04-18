package com.maxkor.feature.detail.impl.domain.util

import java.util.Calendar

private const val DEFAULT_SECOND_VALUE = 0

fun getCalendar(): Calendar =
    Calendar.getInstance()

fun Calendar.setUpCalendar(
    hour: Int,
    minute: Int,
    second: Int = DEFAULT_SECOND_VALUE,
) = apply {
    set(Calendar.HOUR_OF_DAY, hour)
    set(Calendar.MINUTE, minute)
    set(Calendar.SECOND, second)
    isLenient = false
}

fun Calendar.getCalendarTime(): Long = this.timeInMillis