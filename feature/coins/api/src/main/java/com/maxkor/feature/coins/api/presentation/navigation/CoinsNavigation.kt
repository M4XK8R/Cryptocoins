package com.maxkor.feature.coins.api.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavGraphBuilder

interface CoinsNavigation {

    fun graph(
        navGraphBuilder: NavGraphBuilder,
        onCoinCardClick: (coinName: String) -> Unit,
        informUser: (String?) -> Unit,
        lifecycleOwner: LifecycleOwner,
        modifier: Modifier = Modifier,
    )
}