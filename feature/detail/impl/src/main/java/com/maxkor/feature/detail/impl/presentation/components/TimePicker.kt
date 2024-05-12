package com.maxkor.feature.detail.impl.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.maxkor.core.base.data.images.CryptocoinsImages
import com.maxkor.core.ui.components.ConfirmationDialog
import com.maxkor.core.ui.preview.PreviewProvider
import com.maxkor.feature.detail.impl.R

private const val REQUIRED_HEIGHT = 400

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerSwitchable(
    timePickerState: TimePickerState,
    isTimePickerExpanded: Boolean,
    onConfirm: (
        hour: Int,
        minute: Int,
    ) -> Unit,
    onDecline: () -> Unit,
    onToggleClick: () -> Unit,
) {
    val configuration = LocalConfiguration.current

    ConfirmationDialog(
        title = when (isTimePickerExpanded) {
            true -> stringResource(R.string.select_time)
            false -> stringResource(R.string.enter_time)
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
                ToggleButton(
                    isTimePickerExpanded = isTimePickerExpanded,
                    onClick = onToggleClick
                )
            }
        },
        content = {
            if (isTimePickerExpanded &&
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
 * Private sector
 */
@Composable
private fun ToggleButton(
    isTimePickerExpanded: Boolean,
    onClick: () -> Unit,
) = IconButton(
    onClick = onClick
) {
    val switchIcon = when (isTimePickerExpanded) {
        true -> CryptocoinsImages.Collapse
        false -> CryptocoinsImages.Expand
    }
    Icon(
        painter = painterResource(switchIcon),
        contentDescription = when (isTimePickerExpanded) {
            true -> stringResource(R.string.switch_to_text_input)
            false -> stringResource(R.string.switch_to_touch_input)
        }
    )
}

/**
 * Preview
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun TimePickerSwitchablePreview() =
    PreviewProvider(
        content = {
            TimePickerSwitchable(
                timePickerState = rememberTimePickerState(),
                isTimePickerExpanded = true,
                onToggleClick = {},
                onConfirm = { _, _ -> },
                onDecline = {},
            )
        }
    ).DeviceRunnable()


