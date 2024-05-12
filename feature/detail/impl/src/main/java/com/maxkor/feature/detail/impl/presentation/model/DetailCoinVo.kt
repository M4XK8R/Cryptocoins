package com.maxkor.feature.detail.impl.presentation.model

import com.maxkor.feature.detail.impl.domain.model.ExtraDetailCoinInfo

data class DetailCoinVo(
    val name: String,
    val price: String,
    val imageUrl: String,
    val extraInfo: ExtraDetailCoinInfo = ExtraDetailCoinInfo(""),
)