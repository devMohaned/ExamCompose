package com.dev.exam.feature_auth.ui.register

import com.dev.exam.feature_auth.domain.model.LoginResponse
import com.dev.exam.feature_auth.domain.model.RegisterResponse

data class RegisterState(
    val registerResponse: RegisterResponse? = null,
    val isLoading: Boolean = false,
)
