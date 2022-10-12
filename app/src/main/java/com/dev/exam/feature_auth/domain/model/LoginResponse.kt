package com.dev.exam.feature_auth.domain.model

data class LoginResponse(
    val expiration: String,
    val token: String
)