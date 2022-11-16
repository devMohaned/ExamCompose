package com.dev.exam.feature_exams.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.exam.core.util.Resource
import com.dev.exam.feature_exams.domain.model.ExamEntity
import com.dev.exam.feature_exams.domain.model.ExamRequest
import com.dev.exam.feature_exams.domain.usecase.CreateExamUseCase
import com.dev.exam.feature_exams.domain.usecase.GetAllExamsUseCase
import com.dev.exam.feature_exams.domain.usecase.UpdateExamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllExamsUseCase: GetAllExamsUseCase,
    private val createExamUseCase: CreateExamUseCase,
    private val updateExamUseCase: UpdateExamUseCase
) :
    ViewModel() {

    private val _state = MutableStateFlow<ExamState>(ExamState())
    val state: StateFlow<ExamState> get() = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    private fun setLoading() {
        _state.value = _state.value.copy(isLoading = true)
    }

    private fun hideLoading() {
        _state.value = _state.value.copy(isLoading = false)
    }



    fun getAllExams() {
        viewModelScope.launch {
            getAllExamsUseCase()
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    _eventFlow.emit(UIEvent.ShowSnackBar(exception.message.toString()))
                }
                .collect { resource ->
                    hideLoading()
                    when (resource) {
                        is Resource.Error -> _state.value = ExamState()
                        is Resource.Success -> _state.value = ExamState(resource.data ?: emptyList())
                        is Resource.Loading -> setLoading()
                    }
                }
        }
    }


}

data class ExamState(val examList: List<ExamEntity> = emptyList(),val isLoading: Boolean = false)

sealed class UIEvent {
    data class ShowToast(val message: String) : UIEvent()
    data class ShowSnackBar(val message: String) : UIEvent()
}

