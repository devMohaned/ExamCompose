package com.dev.exam.feature_auth.domain.model

data class RegisterRequest(
    val email: String,
    val fullName: String,
    val password: String
)