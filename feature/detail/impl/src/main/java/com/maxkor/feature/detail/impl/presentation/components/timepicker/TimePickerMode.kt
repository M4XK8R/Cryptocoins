package com.maxkor.feature.detail.impl.presentation.components.timepicker

sealed class TimePickerMode {
    data object Pick : TimePickerMode()
    data object Input : TimePickerMode()
}