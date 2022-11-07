package com.dev.exam.feature_exams.data.remote

import com.dev.exam.core.util.WrappedListResponse
import com.dev.exam.core.util.WrappedResponse
import com.dev.exam.feature_exams.data.dto.ExamResponseDTO
import com.dev.exam.feature_exams.domain.model.ExamRequest
import retrofit2.Response
import retrofit2.http.*

interface ExamApi {

    companion object {
        const val EXAMS: String = "api/exams"
        const val EXAMS_BY_ID: String = "api/exams/getexam?id={id}"
    }

    @GET(EXAMS)
    suspend fun getExams() : Response<WrappedListResponse<ExamResponseDTO>>

    @POST(EXAMS)
    suspend fun createExam(@Body examRequest: ExamRequest) : Response<WrappedResponse<ExamResponseDTO>>

    @PUT(EXAMS)
    suspend fun updateExam(@Body examRequest: ExamRequest) : Response<WrappedResponse<ExamResponseDTO>>

    @GET(EXAMS_BY_ID)
    suspend fun getExamById(@Path("id") id: Int) : Response<WrappedResponse<ExamResponseDTO>>


}