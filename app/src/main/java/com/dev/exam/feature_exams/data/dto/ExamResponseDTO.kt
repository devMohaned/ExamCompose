package com.dev.exam.feature_exams.data.dto

import androidx.room.PrimaryKey
import com.dev.exam.feature_auth.domain.model.LoginResponse
import com.dev.exam.feature_auth.domain.model.RegisterResponse
import com.dev.exam.feature_exams.domain.model.ExamEntity
import com.google.gson.annotations.SerializedName

data class ExamResponseDTO(
    val id: Int, val title: String, val description: String, val creatorId: String
) {
    fun toExamEntity(): ExamEntity {
        return ExamEntity(id = id, title =title, description = description, creatorId = creatorId)
    }
}