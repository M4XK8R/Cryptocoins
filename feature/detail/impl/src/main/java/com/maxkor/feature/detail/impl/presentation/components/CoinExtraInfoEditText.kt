package com.maxkor.feature.detail.impl.presentation.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import com.maxkor.feature.detail.impl.R

@Composable
fun CoinExtraInfoEditText(
    value: String,
    onValueChange: (String) -> Unit,
    editTextFontScaling: TextUnit,
    hintFontScaling: TextUnit,
    modifier: Modifier = Modifier,
) = TextField(
    value = value,
    onValueChange = onValueChange,
    modifier = modifier,
    textStyle = LocalTextStyle.current.copy(
        fontSize = editTextFontScaling
    ),
    placeholder = {
        Text(
            text = stringResource(R.string.coin_extra_info_et_hint),
            fontSize = hintFontScaling,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.displaySmall,
            fontFamily = FontFamily.Monospace
        )
    },
    maxLines = 14,
    colors = TextFieldDefaults.colors(
        focusedTextColor = MaterialTheme.colorScheme.onBackground,
    )
)