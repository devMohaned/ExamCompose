package com.dev.exam.feature_exams.domain.usecase

import com.dev.exam.core.util.Resource
import com.dev.exam.core.util.WrappedListResponse
import com.dev.exam.core.util.WrappedResponse
import com.dev.exam.feature_exams.data.dto.ExamResponseDTO
import com.dev.exam.feature_exams.domain.ExamRepo
import com.dev.exam.feature_exams.domain.model.ExamEntity
import com.dev.exam.feature_exams.domain.model.ExamRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateExamUseCase @Inject constructor(
        private val examRepo: ExamRepo,
    ) {
        suspend operator fun invoke(          examRequest: ExamRequest
        ): Flow<Resource<ExamEntity, WrappedResponse<ExamResponseDTO>>> {
            return examRepo.createExam(examRequest)
        }
    }