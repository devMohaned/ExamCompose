package com.dev.exam.feature_exams.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.exam.core.util.Resource
import com.dev.exam.feature_exams.domain.model.ExamEntity
import com.dev.exam.feature_exams.domain.model.ExamRequest
import com.dev.exam.feature_exams.domain.usecase.GetExamByIdUseCase
import com.dev.exam.feature_exams.domain.usecase.UpdateExamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UpdateExamViewModel @Inject constructor(
    private val updateExamUseCase: UpdateExamUseCase,
    private val getExamByIdUseCase: GetExamByIdUseCase
) :
    ViewModel() {
    private var _examId = mutableStateOf<Int>(-1)

    private val _examTitle = mutableStateOf<String>("")
    val examTitle: State<String> = _examTitle

    private val _examDescription = mutableStateOf<String>("")
    val examDescription: State<String> = _examDescription

    private val _state = mutableStateOf<UpdateExamState>(UpdateExamState())
    val state: State<UpdateExamState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow: SharedFlow<UIEvent> = _eventFlow.asSharedFlow()



    fun onExamTitleChanged(title: String) {
        _examTitle.value = title
    }

    fun onExamDescriptionChanged(description: String) {
        _examDescription.value = description
    }

    fun isFormValid(): Boolean = isExamTitleValid() && isExamDescriptionValid()

    private fun isExamTitleValid(): Boolean =
        examTitle.value.isNotEmpty() && examTitle.value.isNotBlank()
                && examTitle.value.length > 3 && examTitle.value.length < 256

    private fun isExamDescriptionValid(): Boolean =
        examDescription.value.isNotEmpty() && examDescription.value.isNotBlank()
                && examDescription.value.length > 3 && examDescription.value.length < 256

    fun getExamById(id: Int) {
        viewModelScope.launch {
            getExamByIdUseCase.invoke(id)
                .onStart {
                    _state.value = _state.value.copy(isLoading = true)
                }
                .catch { exception ->
                    _state.value = _state.value.copy(isLoading = false)
                    _eventFlow.emit(UIEvent.ShowSnackBar(exception.message.toString()))
                }
                .collect { resource ->
                    _state.value = _state.value.copy(isLoading = false)
                    when (resource) {
                        is Resource.Error -> _state.value = UpdateExamState()
                        is Resource.Success -> {
                            initExam(resource.data)
                            _state.value = UpdateExamState(resource.data)
                        }
                        is Resource.Loading -> _state.value = _state.value.copy(isLoading = true)

                    }
                }
        }
    }

    fun updateExam() {
        viewModelScope.launch {
            val examRequest = ExamRequest(
                id = _examId.value,
                description = examDescription.value,
                title = examTitle.value
            )
            updateExamUseCase.invoke(examRequest)
                .onStart {
                    _state.value = _state.value.copy(isLoading = true)
                }
                .catch { exception ->
                    _state.value = _state.value.copy(isLoading = false)
                    _eventFlow.emit(UIEvent.ShowSnackBar(exception.message.toString()))
                }
                .collect { resource ->
                    _state.value = _state.value.copy(isLoading = false)
                    when (resource) {
                        is Resource.Error -> _state.value = UpdateExamState()
                        is Resource.Success -> {
                            _state.value = UpdateExamState(resource.data)
                            _eventFlow.emit(UIEvent.OnExamUpdated)
                        }
                        is Resource.Loading -> _state.value = _state.value.copy(isLoading = true)

                    }
                }
        }
    }


    private fun initExam(examEntity: ExamEntity?) {
        _examId.value = examEntity!!.id
        _examTitle.value = examEntity.title
        _examDescription.value = examEntity.description
    }


    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
        object OnExamUpdated : UIEvent()
    }
}