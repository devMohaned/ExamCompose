package com.dev.exam.feature_auth.data.remote.dto

import com.dev.exam.feature_auth.domain.model.LoginResponse
import com.dev.exam.feature_auth.domain.model.RegisterResponse

data class LoginResponseDTO(
    val expiration: String,
    val token: String
)
{
    fun toLoginResponse(): LoginResponse {
        return LoginResponse(expiration = expiration, token = token)
    }
}