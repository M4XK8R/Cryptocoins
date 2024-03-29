package com.maxkor.feature.detail.impl.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxkor.feature.detail.impl.presentation.viewmodel.DetailViewModel

@Composable
fun DetailRoute(
    name: String,
    price: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    val viewModel: DetailViewModel = hiltViewModel()

    DetailScreen(
        name = name,
        price = price,
        imageUrl = imageUrl,
        modifier = modifier
    )
}
