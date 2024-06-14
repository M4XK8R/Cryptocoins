package com.maxkor.feature.detail.impl.domain.usecase

import com.maxkor.core.base.domain.usecase.UseCase
import com.maxkor.feature.detail.impl.domain.model.ExtraDetailCoinInfo
import com.maxkor.feature.detail.impl.domain.model.parameters.GetCoinExtraInfoParams
import com.maxkor.feature.detail.impl.domain.preferences.DetailPreferences
import javax.inject.Inject

class GetCoinExtraInfoUseCase @Inject constructor(
    private val detailPreferences: DetailPreferences,
) : UseCase<GetCoinExtraInfoParams, ExtraDetailCoinInfo>() {

    override fun execute(parameters: GetCoinExtraInfoParams): ExtraDetailCoinInfo =
        detailPreferences.getExtraCoinInfo(parameters.key)
}