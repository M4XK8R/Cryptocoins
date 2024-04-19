package com.maxkor.feature.coins.impl.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun LifecycleEventObserver(
    lifecycleOwner: LifecycleOwner,
    onEvent: (LifecycleOwner, Lifecycle.Event) -> Unit,
) = DisposableEffect(lifecycleOwner) {
    val observer = LifecycleEventObserver { source, event ->
        onEvent(source, event)
    }
    lifecycleOwner.lifecycle.addObserver(observer)
    onDispose {
        lifecycleOwner.lifecycle.removeObserver(observer)
    }
}
