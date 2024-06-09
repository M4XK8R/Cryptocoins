package com.maxkor.feature.mainactivity.impl.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.maxkor.core.ui.preview.PreviewProvider
import com.maxkor.feature.mainactivity.impl.presentation.navigation.BottomNavigationItem

@Composable
fun NavBottomBar(
    currentRoute: String?,
    navigateToScreen: (route: String) -> Unit,
    navigationItems: List<BottomNavigationItem>,
    modifier: Modifier = Modifier,
) = NavigationBar(
    containerColor = MaterialTheme.colorScheme.primary,
    modifier = modifier
) {
    navigationItems.forEach() { item ->
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

/**
 * Preview
 */
@Composable
@Preview
private fun RunPreviewNavBottomBar() = PreviewProvider(
    content = {
        NavBottomBar(
            currentRoute = null,
            navigationItems = listOf(
                BottomNavigationItem.Home,
                BottomNavigationItem.Favorite
            ),
            navigateToScreen = {}
        )
    }
).DeviceRunnable()









