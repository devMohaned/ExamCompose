package com.dev.exam.ui.temp.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dev.exam.feature_exams.domain.model.ExamEntity
import com.dev.exam.feature_exams.ui.ExamState
import com.dev.exam.feature_exams.ui.HomeViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel: HomeViewModel = hiltViewModel()
    Column {
        Text(text = "Hello this is a a Home page")
        Button(onClick = { viewModel.getAllExams() }) {
            Text(text = "Click me")
        }
    }
}


private fun observe() {


}

private fun handleStateChange(state: ExamState) {
    when (state) {
        is ExamState.Init -> Unit
        is ExamState.ErrorExams -> handleErrorExam(state.rawResponse)
        is ExamState.SuccessExams -> handleSuccessExam(state.examEntity)
        is ExamState.ShowToast -> showToast(state.message)
        is ExamState.IsLoading -> handleLoading(state.isLoading)
    }
}

private fun handleErrorExam(response: String) {
}

private fun handleLoading(isLoading: Boolean) {

}

private fun showToast(message: String) {

}

private fun handleSuccessExam(examEntity: List<ExamEntity>?) {

}