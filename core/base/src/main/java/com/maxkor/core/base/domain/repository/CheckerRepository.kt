package com.maxkor.core.base.domain.repository

interface CheckerRepository {

    fun checkPermission()

    fun checkInternetConnection()
}