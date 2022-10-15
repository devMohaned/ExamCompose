package com.dev.exam.feature_auth.domain.usecase

import com.dev.exam.core.data.local.SharedPrefs

class IsTokenFoundUseCase(private val sharedPrefs: SharedPrefs) {

    operator fun invoke(): Boolean {
        return sharedPrefs.getToken().isNotBlank() && sharedPrefs.getToken().isNotEmpty()
    }
}