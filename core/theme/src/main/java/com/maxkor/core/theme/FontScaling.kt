package com.maxkor.core.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType

data class FontScaling(
    val default: TextUnit = TextUnit(3f, TextUnitType.Em),
    val increasingExtraSmall: TextUnit = TextUnit(3.5f, TextUnitType.Em),
    val increasingSmall: TextUnit = TextUnit(3.75f, TextUnitType.Em),
    val increasingMedium: TextUnit = TextUnit(4f, TextUnitType.Em),
    val increasingLarge: TextUnit = TextUnit(4.5f, TextUnitType.Em),
    val increasingExtraLarge: TextUnit = TextUnit(6f, TextUnitType.Em),
)

val LocalFontScaling = compositionLocalOf { FontScaling() }
