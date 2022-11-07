package com.dev.exam.feature_exams.domain

import com.dev.exam.core.util.Resource
import com.dev.exam.core.util.WrappedListResponse
import com.dev.exam.core.util.WrappedResponse
import com.dev.exam.feature_exams.data.dto.ExamResponseDTO
import com.dev.exam.feature_exams.domain.model.ExamEntity
import com.dev.exam.feature_exams.domain.model.ExamRequest
import kotlinx.coroutines.flow.Flow

interface ExamRepo {
    suspend fun getAllExams(): Flow<Resource<List<ExamEntity>, WrappedListResponse<ExamResponseDTO>>>
    suspend fun createExam(examRequest: ExamRequest): Flow<Resource<ExamEntity, WrappedResponse<ExamResponseDTO>>>
    suspend fun updateExam(examRequest: ExamRequest): Flow<Resource<ExamEntity, WrappedResponse<ExamResponseDTO>>>

}