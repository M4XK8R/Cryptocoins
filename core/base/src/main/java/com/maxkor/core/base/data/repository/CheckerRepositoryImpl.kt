package com.maxkor.core.base.data.repository

import com.maxkor.core.base.domain.repository.CheckerRepository
import javax.inject.Inject

class CheckerRepositoryImpl @Inject constructor() : CheckerRepository {
    override fun checkPermission() {
        TODO("Not yet implemented")
    }

    override fun checkInternetConnection() {
        TODO("Not yet implemented")
    }
}