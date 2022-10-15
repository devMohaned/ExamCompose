package com.dev.exam.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dev.exam.feature_exams.domain.model.ExamEntity

@Database(entities = [ExamEntity::class], version = 1, exportSchema = true)
@TypeConverters(/*Add Your Converters here*/)
abstract class ExamDatabase : RoomDatabase() {


    //abstract val dao: ExamDao
}