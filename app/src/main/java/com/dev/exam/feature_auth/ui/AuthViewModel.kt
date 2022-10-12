package com.dev.exam.feature_auth.ui

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.exam.core.util.Resource
import com.dev.exam.feature_auth.domain.model.LoginRequest
import com.dev.exam.feature_auth.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject


abstract class AuthViewModel : ViewModel() {

    private val _email = mutableStateOf<String>("")
    val email: State<String> = _email

    private val _password = mutableStateOf<String>("")
    val password: State<String> = _password

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun onPasswordChanged(password: String) {
        _password.value = password
    }

    protected fun isEmailValid(): Boolean = Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
    protected fun isPasswordValid(): Boolean =
        !password.value.contains(" ") && password.value.length > 7 && password.value.length < 256
                && password.value.isNotEmpty() && password.value.isNotBlank()

    abstract fun isFormValid(): Boolean


}