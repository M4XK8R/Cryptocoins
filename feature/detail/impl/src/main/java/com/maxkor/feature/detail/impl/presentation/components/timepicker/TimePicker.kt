package com.maxkor.feature.detail.impl.presentation.components.timepicker

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import com.maxkor.core.ui.preview.PreviewProvider

private const val REQUIRED_HEIGHT = 400

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerSwitchable(
    timePickerState: TimePickerState,
    timePickerMode: TimePickerMode,
    changeTimePickerState: () -> Unit,
    onConfirm: (
        hour: Int,
        minute: Int,
    ) -> Unit,
    onDecline: () -> Unit,
) {
    val configuration = LocalConfiguration.current

    TimePickerDialog(
        title = when (timePickerMode) {
            TimePickerMode.Pick -> "Select Time"
            TimePickerMode.Input -> "Enter Time"
        },
        onCancel = { onDecline() },
        onConfirm = {
            onConfirm(
                timePickerState.hour,
                timePickerState.minute
            )
        },
        toggle = {
            if (configuration.screenHeightDp > REQUIRED_HEIGHT) {
                IconButton(
                    onClick = { changeTimePickerState() }
                ) {
                    val switchIcon = when (timePickerMode) {
                        TimePickerMode.Pick -> Icons.Outlined.Build
                        TimePickerMode.Input -> Icons.Outlined.Call
                    }
                    Icon(
                        imageVector = switchIcon,
                        contentDescription = when (timePickerMode) {
                            TimePickerMode.Pick -> "Switch to Text Input"
                            TimePickerMode.Input -> "Switch to Touch Input"
                        }
                    )
                }
            }
        },
        content = {
            if (timePickerMode is TimePickerMode.Pick &&
                configuration.screenHeightDp > REQUIRED_HEIGHT
            ) {
                TimePicker(state = timePickerState)
            } else {
                TimeInput(state = timePickerState)
            }
        }
    )
}

/**
 * Preview
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun TimePickerSwitchablePreview() {
    PreviewProvider(
        content = {
            TimePickerSwitchable(
                timePickerState = rememberTimePickerState(),
                timePickerMode = TimePickerMode.Pick,
                changeTimePickerState = {},
                onConfirm = { _, _ -> },
                onDecline = {},
            )
        }
    ).DeviceRunnable()
}


