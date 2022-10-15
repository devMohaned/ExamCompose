package com.dev.exam.feature_exams.domain

import com.dev.exam.core.util.Resource
import com.dev.exam.core.util.WrappedListResponse
import com.dev.exam.feature_exams.data.dto.ExamResponseDTO
import com.dev.exam.feature_exams.domain.model.ExamEntity
import kotlinx.coroutines.flow.Flow

interface ExamRepo {
    suspend fun getAllExams(): Flow<Resource<List<ExamEntity>, WrappedListResponse<ExamResponseDTO>>>

}