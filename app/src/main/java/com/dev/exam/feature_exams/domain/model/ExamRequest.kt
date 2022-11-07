package com.dev.exam.feature_exams.domain.model

import com.google.gson.annotations.SerializedName

data class ExamRequest(
    val id: Int,
    @SerializedName("descreption")
    val description: String,
    val title: String
)