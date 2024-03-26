package com.maxkor.feature.mainactivity.impl.presentation.components.navbar

import androidx.compose.ui.graphics.vector.ImageVector
import com.maxkor.core.ui.icons.CryptocoinsIcons

sealed class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen,
) {

    data object Home : BottomNavigationItem(
        title = Screen.COINS.name,
        icon = CryptocoinsIcons.Home,
        screen = Screen.COINS
    )

    data object Favorite : BottomNavigationItem(
        title = Screen.FAVORITES.name,
        icon = CryptocoinsIcons.Favorite,
        screen = Screen.FAVORITES
    )

}





