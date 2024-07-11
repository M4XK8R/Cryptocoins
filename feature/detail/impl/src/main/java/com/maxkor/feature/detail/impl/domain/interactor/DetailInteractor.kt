package com.maxkor.feature.detail.impl.domain.interactor

import com.maxkor.feature.detail.impl.domain.model.ExtraDetailCoinInfo
import com.maxkor.feature.detail.impl.domain.model.parameters.CreateReminderParams
import com.maxkor.feature.detail.impl.domain.model.parameters.GetCoinExtraInfoParams
import com.maxkor.feature.detail.impl.domain.model.parameters.SaveCoinExtraInfoParams
import com.maxkor.feature.detail.impl.domain.model.parameters.SavePictureParams
import com.maxkor.feature.detail.impl.domain.model.parameters.SharePictureParams

interface DetailInteractor {

    fun savePicture(savePictureParams: SavePictureParams)

    fun sharePicture(sharePictureParams: SharePictureParams)

    fun createReminder(createReminderParams: CreateReminderParams)

    fun saveCoinExtraInfo(saveCoinExtraInfoParams: SaveCoinExtraInfoParams)

    fun getCoinExtraInfo(getCoinExtraInfoParams: GetCoinExtraInfoParams): ExtraDetailCoinInfo
}