package com.maxkor.feature.detail.impl.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.maxkor.feature.detail.impl.R

@Composable
fun CoinExtraInfoText(
    text: String,
    fontScaling: TextUnit,
    modifier: Modifier = Modifier,
) = Text(
    text = text.ifEmpty {
        stringResource(R.string.coin_extra_info_hint)
    },
    color = MaterialTheme.colorScheme.onBackground,
    fontSize = fontScaling,
    style = MaterialTheme.typography.bodyMedium,
    fontWeight = FontWeight.Medium,
    fontFamily = FontFamily.Default,
    textAlign = TextAlign.Justify,
    maxLines = 14,
    softWrap = true,
    overflow = TextOverflow.Ellipsis,
    modifier = modifier
)