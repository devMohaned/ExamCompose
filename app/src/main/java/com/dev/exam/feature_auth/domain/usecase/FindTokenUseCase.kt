package com.dev.exam.feature_auth.domain.usecase

import com.dev.exam.core.di.DataStoreManager
import com.dev.exam.core.util.Resource
import com.dev.exam.feature_auth.domain.model.LoginRequest
import com.dev.exam.feature_auth.domain.model.LoginResponse
import com.dev.exam.feature_auth.domain.repo.AuthRepo
import kotlinx.coroutines.flow.Flow

class FindTokenUseCase(private val dataStoreManager: DataStoreManager) {

    suspend operator fun invoke(): Boolean {
        return dataStoreManager.doesTokenExist()
    }
}