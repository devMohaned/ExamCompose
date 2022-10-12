package com.dev.exam.ui.temp.register

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
import com.dev.exam.ui.temp.components.EmailInput
import com.dev.exam.ui.temp.components.NameInput
import com.dev.exam.ui.temp.components.PasswordInput
import com.dev.exam.ui.temp.utils.SpacingValues
import com.dev.exam.R
import com.dev.exam.feature_auth.ui.login.LoginButton
import com.dev.exam.feature_auth.ui.login.LoginViewModel
import com.dev.exam.feature_auth.ui.login.SignUpRow
import com.dev.exam.feature_auth.ui.register.RegisterViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onBackButtonClicked: () -> Unit,
    onSignedUp: () -> Unit
) {
    val viewModel: RegisterViewModel = hiltViewModel()
    RegisterForm(modifier,viewModel, false, onBackButtonClicked, onSignedUp) { name, email, password ->
        Log.e("RegFoirm", "Email is $email & password is $password")
        viewModel.register()

    }
}

@Composable
@OptIn(ExperimentalComposeUiApi::class)
private fun RegisterForm(
    modifier: Modifier,
    viewModel: RegisterViewModel = hiltViewModel<RegisterViewModel>(),
    isLoading: Boolean,
    onBackButtonClicked: () -> Unit,
    onSignedUp: () -> Unit,
    onDone: (String, String, String) -> Unit
) {
    val state = viewModel.state
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is RegisterViewModel.UIEvent.ShowSnackBar -> scaffoldState.snackbarHostState.showSnackbar(
                    message = event.message
                )
                is RegisterViewModel.UIEvent.OnSignedUp -> onSignedUp()
            }
        }
    }

    val fullName = viewModel.fullName
    val email = viewModel.email
    val password = viewModel.password
    val isFormValid = rememberSaveable(fullName.value,email.value, password.value) {
        viewModel.isFormValid()
    }

    val isPasswordVisible = rememberSaveable { mutableStateOf(false) }
    val emailFocusRequest = FocusRequester.Default
  //  val passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(scaffoldState = scaffoldState, content = { _ ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 180.dp)
                .verticalScroll(rememberScrollState())
        ) {
            NameInput(nameState = fullName,
                enabled = !isLoading,
                onAction = KeyboardActions { emailFocusRequest.requestFocus() },
                onValueChanged = viewModel::onNameChanged
            )
            EmailInput(modifier = modifier.focusRequester(emailFocusRequest),
                emailState = email,
                enabled = !isLoading,
                onValueChanged = viewModel::onEmailChanged
            )
            PasswordInput(
                passwordState = password,
                enabled = !isLoading,
                onAction = KeyboardActions {
                    if (!isFormValid) return@KeyboardActions
                    onDone(fullName.value,email.value, password.value)
                    keyboardController?.hide()
                },
                isPasswordVisible = isPasswordVisible,
                onValueChanged = viewModel::onPasswordChanged
            )

            RegisterButton(modifier = modifier,
                isLoading = state.value.isLoading,
                validInputs = isFormValid,
                onClick = {
                    onDone(fullName.value, email.value, password.value)
                    keyboardController?.hide()
                })

            Text(modifier = modifier.clickable {
                onBackButtonClicked()
            }, text = stringResource(id = R.string.go_back))
        }
    })



}


@Composable
fun RegisterButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    validInputs: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(SpacingValues.SPACING_NORMAL),
        onClick = onClick,
        enabled = !isLoading && validInputs
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = modifier.size(SpacingValues.SPACING_QUAD))
        } else {
            Text(text = stringResource(id = R.string.register))
        }
    }



}
