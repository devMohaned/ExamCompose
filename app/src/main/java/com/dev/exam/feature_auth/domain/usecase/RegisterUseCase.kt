package com.dev.exam.feature_auth.domain.usecase

import com.dev.exam.core.util.Resource
import com.dev.exam.feature_auth.domain.model.LoginRequest
import com.dev.exam.feature_auth.domain.model.LoginResponse
import com.dev.exam.feature_auth.domain.model.RegisterRequest
import com.dev.exam.feature_auth.domain.model.RegisterResponse
import com.dev.exam.feature_auth.domain.repo.AuthRepo
import kotlinx.coroutines.flow.Flow

class RegisterUseCase(private val authRepo: AuthRepo) {

    operator fun invoke(registerRequest: RegisterRequest): Flow<Resource<RegisterResponse>> {
        return authRepo.register(registerRequest)
    }
}