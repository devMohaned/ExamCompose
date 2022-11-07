package com.dev.exam.feature_exams.di

import com.dev.exam.feature_exams.domain.ExamRepo
import com.dev.exam.feature_exams.domain.model.ExamRequest
import com.dev.exam.feature_exams.domain.usecase.CreateExamUseCase
import com.dev.exam.feature_exams.domain.usecase.GetAllExamsUseCase
import com.dev.exam.feature_exams.domain.usecase.UpdateExamUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module(includes = [ExamModule::class])
@InstallIn(ActivityComponent::class)
object ExamUseCasesModule {

    @Provides
    fun providesGetAllExamsUseCase(examRepo: ExamRepo): GetAllExamsUseCase {
        return GetAllExamsUseCase(examRepo)
    }
    @Provides
    fun providesCreateExamUseCase(examRepo: ExamRepo): CreateExamUseCase {
        return CreateExamUseCase(examRepo)
    }
    @Provides
    fun providesUpdateExamUseCase(examRepo: ExamRepo): UpdateExamUseCase {
        return UpdateExamUseCase(examRepo)
    }
}