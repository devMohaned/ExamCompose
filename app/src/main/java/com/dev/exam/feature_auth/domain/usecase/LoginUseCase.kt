package com.dev.exam.feature_auth.domain.usecase

import com.dev.exam.core.util.Resource
import com.dev.exam.feature_auth.domain.model.LoginRequest
import com.dev.exam.feature_auth.domain.model.LoginResponse
import com.dev.exam.feature_auth.domain.repo.AuthRepo
import kotlinx.coroutines.flow.Flow

class LoginUseCase(private val authRepo: AuthRepo) {

    operator fun invoke(loginRequest: LoginRequest): Flow<Resource<LoginResponse>> {
        return authRepo.login(loginRequest)
    }
}