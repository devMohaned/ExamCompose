package com.dev.exam.feature_exams.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exams")
data class ExamEntity(@PrimaryKey val id:Int, val title: String, val description: String, val creatorId: String?)