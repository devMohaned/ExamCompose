package com.dev.exam.feature_auth.data.remote.dto

import com.dev.exam.feature_auth.domain.model.RegisterResponse
import com.google.gson.annotations.SerializedName

data class RegisterResponseDTO(
    val message: String,
    @SerializedName("data") val token: String,
) {

    fun toRegisterResponse(): RegisterResponse {
        return RegisterResponse(token = token, message = message)
    }
}