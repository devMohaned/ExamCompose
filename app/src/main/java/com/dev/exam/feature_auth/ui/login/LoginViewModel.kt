package com.dev.exam.feature_auth.ui.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.dev.exam.core.util.Resource
import com.dev.exam.feature_auth.domain.model.LoginRequest
import com.dev.exam.feature_auth.domain.usecase.IsTokenFoundUseCase
import com.dev.exam.feature_auth.domain.usecase.LoginUseCase
import com.dev.exam.feature_auth.ui.AuthViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val isTokenFoundUseCase: IsTokenFoundUseCase
) : AuthViewModel() {
    private val _state = mutableStateOf<LoginState>(LoginState())
    val state: State<LoginState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow: SharedFlow<UIEvent> = _eventFlow.asSharedFlow()


    override fun isFormValid(): Boolean = isEmailValid() && isPasswordValid()

    fun login() {
        val loginRequest = LoginRequest(email = email.value, password = password.value)
        viewModelScope.launch {
            loginUseCase(loginRequest).onEach { response ->
                when (response) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            loginResponse = response.data,
                            isLoading = false
                        )
                        _eventFlow.emit(UIEvent.OnLoggedIn)
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            loginResponse = response.data,
                            isLoading = false
                        )
                        _eventFlow.emit(UIEvent.ShowSnackBar(response.message ?: "Unknown Message"))
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            loginResponse = response.data,
                            isLoading = true
                        )
                    }

                }

            }.launchIn(this)
        }

    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
        object OnLoggedIn : UIEvent()
    }
}