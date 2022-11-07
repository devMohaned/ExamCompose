package com.dev.exam.feature_exams.ui

import com.dev.exam.feature_auth.domain.model.LoginResponse
import com.dev.exam.feature_auth.domain.model.RegisterResponse
import com.dev.exam.feature_exams.data.dto.ExamResponseDTO
import com.dev.exam.feature_exams.domain.model.ExamEntity

data class CreateExamState(
    val createExamResponse: ExamEntity? = null,
    val isLoading: Boolean = false,
)
