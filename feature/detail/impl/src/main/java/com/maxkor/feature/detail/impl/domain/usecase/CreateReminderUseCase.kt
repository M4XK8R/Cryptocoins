package com.maxkor.feature.detail.impl.domain.usecase

import com.maxkor.core.base.domain.repository.CheckerRepository
import com.maxkor.core.base.domain.usecase.UseCase
import com.maxkor.feature.detail.impl.domain.model.parameters.CreateReminderParams
import com.maxkor.feature.detail.impl.domain.repository.ReminderRepository
import javax.inject.Inject

class CreateReminderUseCase @Inject constructor(
    private val checkerRepository: CheckerRepository,
    private val reminderRepository: ReminderRepository,
) : UseCase<CreateReminderParams, Unit>() {

    override fun execute(parameters: CreateReminderParams) =
        CheckerRepository.onPermissionState(
            hasPermission = checkerRepository.hasNotificationPermission(),
            hasPermissionCase = {
                reminderRepository.createReminder(
                    coinReminder = parameters.coinReminder,
                    onIncorrectTimeInput = parameters.onIncorrectTimeInput
                )
            },
            noPermissionCase = parameters.noPostNotificationPermissionCase,
        )
}