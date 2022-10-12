package com.dev.exam.feature_auth.data.remote.dto

import com.dev.exam.feature_auth.domain.model.LoginRequest
import com.dev.exam.feature_auth.domain.model.RegisterRequest

data class RegisterRequestDTO(
    val email: String,
    val fullName: String,
    val password: String
){
    fun toLRegisterRequest(): RegisterRequest {
        return RegisterRequest(fullName = fullName, email = email, password = password)
    }
}