package com.dev.exam.feature_auth.data.remote.dto

import com.dev.exam.feature_auth.domain.model.RegisterResponse

data class RegisterResponseDTO(
    val message: String,
    val status: String
) {

    fun toRegisterResponse(): RegisterResponse {
        return RegisterResponse(message = message, status = status)
    }
}