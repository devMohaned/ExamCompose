package com.dev.exam.feature_questions.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier


@Composable
fun AddQuestionSuccessScreen(modifier: Modifier = Modifier) {
    Column {
        val questionOne = rememberSaveable {
            (   mutableStateOf(""))
        }
        val questionTwo = rememberSaveable {
            (   mutableStateOf(""))
        }
        val questionThree = rememberSaveable {
            (   mutableStateOf(""))
        }
        QuestionTextField(
            value = questionOne.value,
            onValueChanged = { questionOne.value = it },
            label = "Question Title"
        )

        QuestionTextField(
            value = questionTwo.value,
            onValueChanged = { questionTwo.value = it },
            label = "Question Title"
        )

        QuestionTextField(
            value = questionThree.value,
            onValueChanged = { questionThree.value = it },
            label = "Question Title"
        )
    }
}


@Composable
fun QuestionTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (value: String) -> Unit,
    label: String,
    isLoading: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier,
        enabled = !isLoading,
        label = { Text(text = label) }
    )


}