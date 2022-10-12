package com.dev.exam.feature_auth.domain.repo

import com.dev.exam.core.util.Resource
import com.dev.exam.feature_auth.domain.model.LoginRequest
import com.dev.exam.feature_auth.domain.model.LoginResponse
import com.dev.exam.feature_auth.domain.model.RegisterRequest
import com.dev.exam.feature_auth.domain.model.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepo {

    fun login(loginRequest: LoginRequest): Flow<Resource<LoginResponse>>

    fun register(registerRequest: RegisterRequest): Flow<Resource<RegisterResponse>>
}