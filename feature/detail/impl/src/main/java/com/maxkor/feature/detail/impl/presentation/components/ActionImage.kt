package com.maxkor.feature.detail.impl.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.maxkor.feature.detail.impl.R

@Composable
fun ActionImage(
    imageResId: Int,
    onClick: () -> Unit,
) = Image(
    painter = painterResource(id = imageResId),
    contentDescription = stringResource(R.string.action_image),
    modifier = Modifier.clickable { onClick() }
)