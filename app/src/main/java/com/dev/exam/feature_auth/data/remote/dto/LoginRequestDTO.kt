package com.dev.exam.feature_auth.data.remote.dto

import com.dev.exam.feature_auth.domain.model.LoginRequest
import com.dev.exam.feature_auth.domain.model.RegisterResponse

data class LoginRequestDTO(
    val email: String,
    val password: String
){
    fun toLoginRequest(): LoginRequest {
        return LoginRequest(email = email, password = password)
    }
}