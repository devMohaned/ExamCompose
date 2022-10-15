package com.dev.exam.feature_exams.di

import com.dev.exam.core.data.local.SharedPrefs
import com.dev.exam.feature_auth.data.remote.AuthApi
import com.dev.exam.feature_auth.data.repo.AuthRepoImpl
import com.dev.exam.feature_auth.domain.repo.AuthRepo
import com.dev.exam.feature_auth.domain.usecase.IsTokenFoundUseCase
import com.dev.exam.feature_auth.domain.usecase.LoginUseCase
import com.dev.exam.feature_auth.domain.usecase.RegisterUseCase
import com.dev.exam.feature_exams.data.remote.ExamApi
import com.dev.exam.feature_exams.data.repo.ExamRepoImpl
import com.dev.exam.feature_exams.domain.ExamRepo
import com.dev.exam.feature_exams.domain.usecase.GetAllExamsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExamModule {

    @Provides
    fun providesGetAllExams(examRepo: ExamRepo): GetAllExamsUseCase {
        return GetAllExamsUseCase(examRepo)
    }

    @Provides
    fun providesExamRepository(examApi: ExamApi): ExamRepo {
        return ExamRepoImpl(examApi)
    }


    @Provides
    fun providesExamApi(retrofit: Retrofit): ExamApi {
        return retrofit.create(ExamApi::class.java)
    }


}