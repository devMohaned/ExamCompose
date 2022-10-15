package com.dev.exam.feature_auth.domain.model

data class LoginResponse(
    val message: String,
    val token: String
)