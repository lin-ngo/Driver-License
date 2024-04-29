package com.ngotrieuvi544.driverlicense.data.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ngotrieuvi544.driverlicense.data.model.Exam
import com.ngotrieuvi544.driverlicense.data.model.NewQuestion
import com.ngotrieuvi544.driverlicense.data.model.StudyCategory
import com.ngotrieuvi544.driverlicense.data.model.WrongAnswer
import com.ngotrieuvi544.driverlicense.data.model.roomtypeconverter.ExamTypeConverter
import com.ngotrieuvi544.driverlicense.data.model.roomtypeconverter.QuestionOptionTypeConverter
import com.ngotrieuvi544.driverlicense.data.model.roomtypeconverter.QuestionTypeConverter
import com.ngotrieuvi544.driverlicense.data.model.roomtypeconverter.WrongAnswerObjectTypeConverter
import com.ngotrieuvi544.driverlicense.data.repository.local.MotorbikeAppDatabase.Companion.ALLOW_EXPORT_SCHEMA
import com.ngotrieuvi544.driverlicense.data.repository.local.MotorbikeAppDatabase.Companion.DATABASE_VERSION
import com.ngotrieuvi544.driverlicense.data.repository.local.exam.ExamDao
import com.ngotrieuvi544.driverlicense.data.repository.local.question.QuestionsDao
import com.ngotrieuvi544.driverlicense.data.repository.local.study.StudyDao
import com.ngotrieuvi544.driverlicense.data.repository.local.wronganswer.WrongAnswerDao

@Database(
    entities = [
        Exam::class,
        NewQuestion::class,
        StudyCategory::class,
        WrongAnswer::class,
    ],
    version = DATABASE_VERSION,
    exportSchema = ALLOW_EXPORT_SCHEMA,
)
@TypeConverters(
    ExamTypeConverter::class,
    QuestionTypeConverter::class,
    QuestionOptionTypeConverter::class,
    WrongAnswerObjectTypeConverter::class,
)
abstract class MotorbikeAppDatabase : RoomDatabase() {
    abstract fun getExamDao(): ExamDao
    abstract fun getQuestionDao(): QuestionsDao
    abstract fun getStudyDao(): StudyDao
    abstract fun getWrongAnswerDao(): WrongAnswerDao

    companion object {
        const val DATABASE_NAME = "MotorbikeAppDatabase"
        const val DATABASE_VERSION = 1
        const val ALLOW_EXPORT_SCHEMA = false
    }
}
