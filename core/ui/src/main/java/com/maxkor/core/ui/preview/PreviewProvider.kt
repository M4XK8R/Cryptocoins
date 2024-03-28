package com.maxkor.core.ui.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.maxkor.core.theme.CryptocoinsTheme

class PreviewProvider(
    private val content: @Composable () -> Unit,
) {
    @Composable
    fun DeviceRunnable() {
        CryptocoinsTheme {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                content()
            }
        }
    }

    @Composable
    fun DefaultMode() {
        CryptocoinsTheme {
            Surface() {
                content()
            }
        }
    }

}