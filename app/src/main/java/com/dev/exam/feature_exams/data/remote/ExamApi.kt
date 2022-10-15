package com.dev.exam.feature_exams.data.remote

import com.dev.exam.core.util.WrappedListResponse
import com.dev.exam.feature_exams.data.dto.ExamResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface ExamApi {

    companion object {
        const val EXAMS: String = "api/exams"
    }

    @GET(EXAMS)
    suspend fun getExams() : Response<WrappedListResponse<ExamResponseDTO>>



}