package com.maxkor.feature.mainactivity.impl.presentation.components.navbar

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType

@Composable
fun NavBottomBar(
    currentRoute: String?,
    navigateToScreen: (route: String) -> Unit,
) {
    val navItems = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Favorite
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
    ) {
        navItems.forEach() { item ->
            val route = item.screen.route

            NavigationBarItem(
                selected = route == currentRoute,
                onClick = {
                    if (route != currentRoute) {
                        navigateToScreen(route)
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.icon.name,
//                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = TextUnit(12f, TextUnitType.Sp),
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onBackground,
                    unselectedIconColor = MaterialTheme.colorScheme.background,
                )
            )
        }
    }
}









