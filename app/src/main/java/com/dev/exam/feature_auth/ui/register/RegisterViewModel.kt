package com.dev.exam.feature_auth.ui.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.dev.exam.core.util.Resource
import com.dev.exam.feature_auth.domain.model.LoginRequest
import com.dev.exam.feature_auth.domain.model.RegisterRequest
import com.dev.exam.feature_auth.domain.usecase.LoginUseCase
import com.dev.exam.feature_auth.domain.usecase.RegisterUseCase
import com.dev.exam.feature_auth.ui.AuthViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :
    AuthViewModel() {
    private val _fullName = mutableStateOf<String>("")
    val fullName: State<String> = _fullName

    private val _state = mutableStateOf<RegisterState>(RegisterState())
    val state: State<RegisterState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow: SharedFlow<UIEvent> = _eventFlow.asSharedFlow()

    fun onNameChanged(name: String) {
        _fullName.value = name
    }

    override fun isFormValid(): Boolean = isEmailValid() && isPasswordValid() && isFullNameValid()

    private fun isFullNameValid(): Boolean =
        fullName.value.isNotEmpty() && fullName.value.isNotBlank() && fullName.value.length > 3 && fullName.value.length < 256

    fun register() {
        val registerRequest = RegisterRequest(
            fullName = fullName.value,
            email = email.value,
            password = password.value
        )
        viewModelScope.launch {
            registerUseCase(registerRequest).onEach { response ->
                when (response) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            registerResponse = response.data,
                            isLoading = false
                        )
                        _eventFlow.emit(UIEvent.OnSignedUp)
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            registerResponse = response.data,
                            isLoading = false
                        )
                        _eventFlow.emit(UIEvent.ShowSnackBar(response.message ?: "Unknown Message"))
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            registerResponse = response.data,
                            isLoading = true
                        )
                    }

                }

            }.launchIn(this)
        }

    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
        object OnSignedUp : UIEvent()
    }
}