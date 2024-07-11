package com.maxkor.feature.detail.impl.domain.interactor.impl

import com.maxkor.core.base.domain.usecase.UseCase
import com.maxkor.feature.detail.impl.domain.interactor.DetailInteractor
import com.maxkor.feature.detail.impl.domain.model.ExtraDetailCoinInfo
import com.maxkor.feature.detail.impl.domain.model.parameters.CreateReminderParams
import com.maxkor.feature.detail.impl.domain.model.parameters.GetCoinExtraInfoParams
import com.maxkor.feature.detail.impl.domain.model.parameters.SaveCoinExtraInfoParams
import com.maxkor.feature.detail.impl.domain.model.parameters.SavePictureParams
import com.maxkor.feature.detail.impl.domain.model.parameters.SharePictureParams
import com.maxkor.feature.detail.impl.domain.usecase.CreateReminderUseCase
import com.maxkor.feature.detail.impl.domain.usecase.GetCoinExtraInfoUseCase
import com.maxkor.feature.detail.impl.domain.usecase.SaveCoinExtraInfoUseCase
import com.maxkor.feature.detail.impl.domain.usecase.SavePictureUseCase
import com.maxkor.feature.detail.impl.domain.usecase.SharePictureUseCase
import javax.inject.Inject

class DetailInteractorImpl @Inject constructor(
    private val savePictureUseCase: SavePictureUseCase,
    private val sharePictureUseCase: SharePictureUseCase,
    private val createReminderUseCase: CreateReminderUseCase,
    private val saveCoinExtraInfoUseCase: SaveCoinExtraInfoUseCase,
    private val getCoinExtraInfoUseCase: GetCoinExtraInfoUseCase,
) : DetailInteractor {

    override fun savePicture(savePictureParams: SavePictureParams) {
        savePictureUseCase.invoke(savePictureParams)
    }

    override fun sharePicture(sharePictureParams: SharePictureParams) {
        sharePictureUseCase.invoke(sharePictureParams)
    }

    override fun createReminder(createReminderParams: CreateReminderParams) {
        createReminderUseCase.invoke(createReminderParams)
    }

    override fun saveCoinExtraInfo(saveCoinExtraInfoParams: SaveCoinExtraInfoParams) {
        saveCoinExtraInfoUseCase.invoke(saveCoinExtraInfoParams)
    }

    override fun getCoinExtraInfo(
        getCoinExtraInfoParams: GetCoinExtraInfoParams,
    ): ExtraDetailCoinInfo =
        when (val result = getCoinExtraInfoUseCase.invoke(getCoinExtraInfoParams)) {
            is UseCase.Result.Success -> result.value
            is UseCase.Result.Failure -> ExtraDetailCoinInfo.Empty
        }
}
