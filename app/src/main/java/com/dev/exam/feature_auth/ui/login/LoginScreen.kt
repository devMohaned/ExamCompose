package com.dev.exam.feature_auth.ui.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dev.exam.R
import com.dev.exam.ui.temp.components.EmailInput
import com.dev.exam.ui.temp.components.PasswordInput
import com.dev.exam.ui.temp.utils.SpacingValues.SPACING_NORMAL
import com.dev.exam.ui.temp.utils.SpacingValues.SPACING_QUAD
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onSignUpClicked: () -> Unit,
    onLoggedIn: () -> Unit,
    ) {

    val viewModel: LoginViewModel = hiltViewModel()

    LoginForm(modifier, viewModel, false, onSignUpClicked, onLoggedIn) { email, password ->
        Log.e("LoginForm", "Email is $email & password is $password")
        viewModel.login()
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalComposeUiApi::class)
private fun LoginForm(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel<LoginViewModel>(),
    isLoading: Boolean,
    onSignUpClicked: () -> Unit,
    onLoggedIn: () -> Unit,
    onDone: (String, String) -> Unit,
) {
    val state = viewModel.state
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is LoginViewModel.UIEvent.ShowSnackBar -> scaffoldState.snackbarHostState.showSnackbar(
                    message = event.message
                )
                is LoginViewModel.UIEvent.OnLoggedIn -> onLoggedIn()
            }
        }


    }

    val email = viewModel.email
    val password = viewModel.password
    val isFormValid = rememberSaveable(email.value, password.value) {
        viewModel.isFormValid()
    }

    val isPasswordVisible = rememberSaveable { mutableStateOf(false) }
    val passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(scaffoldState = scaffoldState, content = { _ ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 180.dp)
                .verticalScroll(rememberScrollState())
        ) {
            EmailInput(
                emailState = email,
                enabled = !isLoading,
                onAction = KeyboardActions { passwordFocusRequest.requestFocus() },
                onValueChanged = viewModel::onEmailChanged
            )
            PasswordInput(
                modifier = modifier.focusRequester(passwordFocusRequest),
                passwordState = password,
                enabled = !isLoading,
                onAction = KeyboardActions {
                    if (!isFormValid) return@KeyboardActions
                    onDone(email.value, password.value)
                    keyboardController?.hide()
                },
                isPasswordVisible = isPasswordVisible,
                onValueChanged = viewModel::onPasswordChanged
            )

            LoginButton(
                modifier = modifier,
                isLoading = state.value.isLoading,
                validInputs = isFormValid,
                onClick = {
                    onDone(email.value, password.value)
                    keyboardController?.hide()
                })

            SignUpRow(modifier = modifier) {
                onSignUpClicked()
            }
        }
    })

}

@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    validInputs: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(SPACING_NORMAL),
        onClick = onClick,
        enabled = !isLoading && validInputs
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = modifier.size(SPACING_QUAD))
        } else {
            Text(text = stringResource(id = R.string.login))
        }
    }

}


@Composable
fun SignUpRow(modifier: Modifier = Modifier, onSignUpClicked: () -> Unit) {
    Row {
        Text(text = stringResource(id = R.string.create_new_account))
        Text(modifier = modifier.clickable {
            onSignUpClicked()
        }, text = stringResource(id = R.string.sign_up), style = MaterialTheme.typography.h5)
    }
}
