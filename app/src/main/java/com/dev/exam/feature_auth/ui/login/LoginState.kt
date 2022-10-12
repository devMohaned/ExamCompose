package com.dev.exam.feature_auth.ui.login

import com.dev.exam.feature_auth.domain.model.LoginResponse

data class LoginState(
    val loginResponse: LoginResponse? = null,
    val isLoading: Boolean = false,
)
