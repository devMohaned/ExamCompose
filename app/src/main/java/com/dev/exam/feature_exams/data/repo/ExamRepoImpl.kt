package com.dev.exam.feature_exams.data.repo

import com.dev.exam.core.util.Resource
import com.dev.exam.core.util.WrappedListResponse
import com.dev.exam.core.util.WrappedResponse
import com.dev.exam.feature_exams.data.dto.ExamResponseDTO
import com.dev.exam.feature_exams.data.remote.ExamApi
import com.dev.exam.feature_exams.domain.ExamRepo
import com.dev.exam.feature_exams.domain.model.ExamEntity
import com.dev.exam.feature_exams.domain.model.ExamRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ExamRepoImpl @Inject constructor(private val examApi: ExamApi) : ExamRepo {
    override suspend fun getAllExams(): Flow<Resource<List<ExamEntity>, WrappedListResponse<ExamResponseDTO>>> {
        return flow {
            emit(Resource.Loading())
            val response = examApi.getExams()
            if (response.isSuccessful) {
                val body = response.body()!!
                val exams = mutableListOf<ExamEntity>()
                body.data?.forEach { examResponse ->
                    exams.add(
                        ExamEntity(
                            examResponse.id,
                            examResponse.title,
                            examResponse.descreption,
                            examResponse.creatorId
                        )
                    )
                }
                emit(Resource.Success(exams))
            } else {
                val type = object : TypeToken<WrappedListResponse<ExamResponseDTO>>() {}.type
                val err = Gson().fromJson<WrappedListResponse<ExamResponseDTO>>(
                    response.errorBody()!!.charStream(), type
                )!!
                err.code = response.code()
                emit(Resource.Error(err, message = err.message))
            }
        }
    }

    override suspend fun createExam(examRequest: ExamRequest): Flow<Resource<ExamEntity, WrappedResponse<ExamResponseDTO>>> {
        return flow {
            emit(Resource.Loading())
            val response = examApi.createExam(examRequest)
            if (response.isSuccessful) {
                val body = response.body()!!
                var exam: ExamEntity? = null
                body.data?.let { examResponse ->
                    exam = examResponse.toExamEntity()
                }
                emit(Resource.Success(exam))
            } else {
                val type = object : TypeToken<WrappedResponse<ExamResponseDTO>>() {}.type
                val err = Gson().fromJson<WrappedResponse<ExamResponseDTO>>(
                    response.errorBody()!!.charStream(), type
                )!!
                err.code = response.code()
                emit(Resource.Error(err, message = err.message))
            }
        }
    }


    override suspend fun updateExam(examRequest: ExamRequest): Flow<Resource<ExamEntity, WrappedResponse<ExamResponseDTO>>> {
        return flow {
            emit(Resource.Loading())
            val response = examApi.updateExam(examRequest)
            if (response.isSuccessful) {
                val body = response.body()!!
                var exam: ExamEntity? = null
                body.data?.let { examResponse ->
                    exam = examResponse.toExamEntity()
                }
                emit(Resource.Success(exam))
            } else {
                val type = object : TypeToken<WrappedResponse<ExamResponseDTO>>() {}.type
                val err = Gson().fromJson<WrappedResponse<ExamResponseDTO>>(
                    response.errorBody()!!.charStream(), type
                )!!
                err.code = response.code()
                emit(Resource.Error(err, message = err.message))
            }
        }
    }
}
