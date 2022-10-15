package com.dev.exam.ui

import androidx.lifecycle.ViewModel
import com.dev.exam.feature_auth.domain.usecase.IsTokenFoundUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val isTokenFoundUseCase: IsTokenFoundUseCase
) : ViewModel() {

    fun isTokenFound(): Boolean {
        return isTokenFoundUseCase()
    }

}