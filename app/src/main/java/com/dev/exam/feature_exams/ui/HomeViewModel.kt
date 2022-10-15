package com.dev.exam.feature_exams.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.exam.core.util.Resource
import com.dev.exam.core.util.WrappedResponse
import com.dev.exam.feature_exams.data.dto.ExamResponseDTO
import com.dev.exam.feature_exams.domain.model.ExamEntity
import com.dev.exam.feature_exams.domain.usecase.GetAllExamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getAllExamsUseCase: GetAllExamsUseCase) :
    ViewModel() {

    private val state = MutableStateFlow<ExamState>(ExamState.Init)
    val mState: StateFlow<ExamState> get() = state


    private fun setLoading() {
        state.value = ExamState.IsLoading(true)
    }

    private fun hideLoading() {
        state.value = ExamState.IsLoading(false)
    }

    private fun showToast(message: String) {
        state.value = ExamState.ShowToast(message)
    }


    fun getAllExams() {
        viewModelScope.launch {
            getAllExamsUseCase()
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.message.toString())
                }
                .collect { resource ->
                    hideLoading()
                    when (resource) {
                        is Resource.Error -> state.value = ExamState.ErrorExams(resource.message)
                        is Resource.Success -> state.value = ExamState.SuccessExams(resource.data)
                        is Resource.Loading -> setLoading()
                    }
                }
        }
    }


}

sealed class ExamState {
    object Init : ExamState()
    data class IsLoading(val isLoading: Boolean) : ExamState()
    data class ShowToast(val message: String) : ExamState()
    data class SuccessExams(val examEntity: List<ExamEntity>?) : ExamState()
    data class ErrorExams(val rawResponse: String) : ExamState()
}

