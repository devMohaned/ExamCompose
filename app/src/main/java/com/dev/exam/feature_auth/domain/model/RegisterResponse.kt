package com.dev.exam.feature_auth.domain.model

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    val message: String,
   val token: String,
)