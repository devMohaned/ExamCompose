package com.dev.exam.feature_exams.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.dev.exam.ui.temp.components.NameInput
import com.dev.exam.ui.temp.utils.SpacingValues
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CreateExamScreen(
    modifier: Modifier = Modifier,
    onBackButtonClicked: () -> Unit,
    onCreateExamClicked: () -> Unit
) {
    val viewModel: CreateExamViewModel = hiltViewModel()
    CreateExamForm(modifier, viewModel,false, onBackButtonClicked, onCreateExamClicked) {
            title, description ->
        viewModel.createExam()

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalComposeUiApi::class)
private fun CreateExamForm(
    modifier: Modifier,
    viewModel: CreateExamViewModel = hiltViewModel<CreateExamViewModel>(),
    isLoading: Boolean,
    onBackButtonClicked: () -> Unit,
    onExamCreated: () -> Unit,
    onDone: (String, String) -> Unit
) {
    val state = viewModel.state
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is CreateExamViewModel.UIEvent.ShowSnackBar -> scaffoldState.snackbarHostState.showSnackbar(
                    message = event.message
                )
                is CreateExamViewModel.UIEvent.OnExamCreated -> onExamCreated()
            }
        }
    }

    val examTitle = viewModel.examTitle
    val examDescription = viewModel.examDescription

    val isFormValid = rememberSaveable(examTitle.value, examDescription.value) {
        viewModel.isFormValid()
    }

    val descriptionFocus = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(scaffoldState = scaffoldState, content = { _ ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 180.dp)
                .verticalScroll(rememberScrollState())
        ) {
            NameInput(nameState = examTitle,
                enabled = !isLoading,
                onAction = KeyboardActions { descriptionFocus.requestFocus() },
                onValueChanged = viewModel::onExamTitleChanged
            )
            NameInput(modifier = modifier.focusRequester(descriptionFocus),
                nameState = examDescription,
                enabled = !isLoading,
                onValueChanged = viewModel::onExamDescriptionChanged
            )

            CreateExamButton(modifier = modifier,
                isLoading = state.value.isLoading,
                validInputs = isFormValid,
                onClick = {
                    onDone(examTitle.value, examDescription.value)
                    keyboardController?.hide()
                })

            Text(modifier = modifier.clickable {
                onBackButtonClicked()
            }, text = stringResource(id = R.string.go_back))
        }
    })



}


@Composable
fun CreateExamButton(
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
            Text(text = stringResource(id = R.string.create_exam))
        }
    }



}
