package com.maxkor.feature.coins.impl.domain.model.parameters

    data class DownloadAndUpdateCoinsParams(
    val informUserOnFailure: (String) -> Unit,
)
