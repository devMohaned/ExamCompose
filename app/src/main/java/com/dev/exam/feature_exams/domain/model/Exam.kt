package com.dev.exam.feature_exams.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exams")
data class Exam(@PrimaryKey val name:String)
