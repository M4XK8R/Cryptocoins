package com.maxkor.core.ui.components.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.maxkor.core.theme.LocalSpacing
import com.maxkor.core.ui.preview.PreviewProvider

@Composable
fun ConfirmationDialog(
    title: String,
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    toggle: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    val spacing = LocalSpacing.current

    Dialog(
        onDismissRequest = onCancel,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = spacing.spaceExtraSmall,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.surface
                )
                .then(modifier)
        ) {
            Column(
                modifier = Modifier.padding(spacing.spaceExtraLarge),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = spacing.spaceLarge),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(spacing.spaceUltraLarge)
                        .fillMaxWidth()
                ) {
                    toggle()
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = onCancel) {
                        Text(
                            stringResource(com.maxkor.core.base.R.string.cancel)
                        )
                    }
                    TextButton(onClick = onConfirm) {
                        Text(
                            stringResource(com.maxkor.core.base.R.string.ok)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Preview
 */
@Composable
@Preview
fun TimePickerDialogPreview() {
    PreviewProvider(
        content = {
            ConfirmationDialog(
                title = "Confirmation Dialog",
                onCancel = { },
                onConfirm = { },
                toggle = { },
                content = {}
            )
        }
    ).DeviceRunnable()
}