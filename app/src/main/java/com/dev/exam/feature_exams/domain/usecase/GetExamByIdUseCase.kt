package com.dev.exam.feature_exams.domain.usecase

import com.dev.exam.core.util.Resource
import com.dev.exam.core.util.WrappedListResponse
import com.dev.exam.feature_exams.data.dto.ExamResponseDTO
import com.dev.exam.feature_exams.domain.ExamRepo
import com.dev.exam.feature_exams.domain.model.ExamEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetExamByIdUseCase @Inject constructor(private val examRepo: ExamRepo) {

      suspend operator fun invoke(id: Int) : Flow<Resource<ExamEntity, WrappedListResponse<ExamResponseDTO>>> {
        return examRepo.getExamById(id)
    }
}