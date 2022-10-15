package com.dev.exam.feature_auth.data.remote.dto

import com.dev.exam.feature_auth.domain.model.LoginResponse
import com.dev.exam.feature_auth.domain.model.RegisterResponse
import com.google.gson.annotations.SerializedName

data class LoginResponseDTO(
    val message: String,
  @SerializedName("data")  val token: String
)
{
    fun toLoginResponse(): LoginResponse {
        return LoginResponse(message = message, token = token)
    }
}