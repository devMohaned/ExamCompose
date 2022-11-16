package com.dev.exam.feature_exams.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dev.exam.feature_exams.domain.model.ExamEntity
import com.dev.exam.ui.temp.utils.SpacingValues

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onExamCreatedButtonClicked: () -> Unit,
    onUpdateExamButtonClicked: (examId: Int) -> Unit,
    onAddQuestionPerExamButtonClicked: (examId: Int) -> Unit
) {
    val state = viewModel.state.collectAsState()
    val scaffoldState = rememberScaffoldState()


    Scaffold(scaffoldState = scaffoldState) {
        Column(modifier = modifier.fillMaxSize()) {
            Text(text = "Exams!")
            Spacer(modifier = modifier.padding(SpacingValues.SPACING_NORMAL))
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                items(state.value.examList.size) { i ->
                    ExamItem(
                        state.value.examList[i],
                        onUpdateExamButtonClicked,
                        onAddQuestionPerExamButtonClicked
                    )
                }
            }
        }
    }

    Column {
        Text(text = "Hello this is a a Home page")
        Button(onClick = { viewModel.getAllExams() }) {
            Text(text = "Get All Exams")
        }

        Button(onClick = { onExamCreatedButtonClicked() }) {
            Text(text = "Create Exam")
        }


    }
}


@Composable
fun ExamItem(
    examEntity: ExamEntity,
    onUpdateExamButtonClicked: (examId: Int) -> Unit,
    onAddQuestionPerExamButtonClicked: (examId: Int) -> Unit
) {
    Column {
        Text(text = "Exam Title: ${examEntity.title}", style = MaterialTheme.typography.h3)

        Text(text = "Description: ${examEntity.description}")

        Button(onClick = { onAddQuestionPerExamButtonClicked(examEntity.id) }) {
            Text(text = "Add Questions to Exam")
        }

        Button(onClick = { onUpdateExamButtonClicked(examEntity.id) }) {
            Text(text = "Update Exam")
        }
    }
}
