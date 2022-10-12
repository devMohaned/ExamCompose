package com.dev.exam.ui.temp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.dev.exam.R
import com.dev.exam.ui.temp.utils.SpacingValues


@Composable
fun InputField(
    modifier: Modifier = Modifier, valueState: State<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(

        value = valueState.value,
        onValueChange = onValueChanged/*{ valueState.value = it }*/,
        singleLine = isSingleLine,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        keyboardActions = onAction,
        label = { Text(text = labelId) },
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = SpacingValues.SPACING_NORMAL,
                end = SpacingValues.SPACING_NORMAL,
                top = SpacingValues.SPACING_SMALL,
                bottom = SpacingValues.SPACING_SMALL
            ),
    )
}



@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    passwordState: State<String>,
    isPasswordVisible: MutableState<Boolean>,
    labelId: String = stringResource(
        id = R.string.password
    ),
    singleLine: Boolean = true,
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions,
    onValueChanged: (String) -> Unit

) {
    val visualTransformation =
        if (isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = SpacingValues.SPACING_NORMAL,
                end = SpacingValues.SPACING_NORMAL,
                top = SpacingValues.SPACING_SMALL,
                bottom = SpacingValues.SPACING_SMALL
            ),
        value = passwordState.value,
        onValueChange = onValueChanged/*{ passwordState.value = it }*/,
        label = {
            Text(text = labelId)
        },
        enabled = enabled,
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        keyboardActions = onAction,
        visualTransformation = visualTransformation,
        trailingIcon = { PasswordVisibility(isPasswordVisible = isPasswordVisible) }
    )
}

@Composable
fun PasswordVisibility(isPasswordVisible: MutableState<Boolean>) {
    IconButton(onClick = { isPasswordVisible.value = !isPasswordVisible.value }) {
        Icons.Default.Close
    }
}

@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    emailState: State<String>,
    labelId: String = stringResource(
        id = R.string.email
    ),
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    onValueChanged: (String) -> Unit

) {
    InputField(
        modifier = modifier,
        valueState = emailState,
        labelId = labelId,
        enabled = enabled,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction, onAction = onAction,
        onValueChanged = onValueChanged
    )
}


@Composable
fun NameInput(
    modifier: Modifier = Modifier,
    nameState: State<String>,
    labelId: String = stringResource(
        id = R.string.name
    ),
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    onValueChanged: (String) -> Unit

) {
    InputField(
        modifier = modifier,
        valueState = nameState,
        labelId = labelId,
        enabled = enabled,
        keyboardType = KeyboardType.Text,
        imeAction = imeAction, onAction = onAction,
        onValueChanged = onValueChanged

    )
}
