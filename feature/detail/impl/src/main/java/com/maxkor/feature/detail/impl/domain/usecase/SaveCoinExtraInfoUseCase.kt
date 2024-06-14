package com.maxkor.feature.detail.impl.domain.usecase

import com.maxkor.core.base.domain.usecase.UseCase
import com.maxkor.feature.detail.impl.domain.model.parameters.SaveCoinExtraInfoParams
import com.maxkor.feature.detail.impl.domain.preferences.DetailPreferences
import javax.inject.Inject

class SaveCoinExtraInfoUseCase @Inject constructor(
    private val detailPreferences: DetailPreferences,
) : UseCase<SaveCoinExtraInfoParams, Unit>() {

    override fun execute(parameters: SaveCoinExtraInfoParams) =
        detailPreferences.saveCoinExtraInfo(
            key = parameters.key,
            extraInfo = parameters.extraInfo.value
        )
}
