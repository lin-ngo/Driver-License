package com.ngotrieuvi544.driverlicense.data.repository.local.exam

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ngotrieuvi544.driverlicense.data.model.Exam

@Dao
interface ExamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewExam(exam: Exam)

    @Update
    suspend fun updateExam(exam: Exam)

    @Delete
    suspend fun deleteExam(exam: Exam)

    @Query("select * from EXAM")
    suspend fun getAllExam(): MutableList<Exam>

    @Query("select * from EXAM where id=:id")
    suspend fun getDetailExamByID(id: Int): Exam

    @Query("select * from EXAM where examType = :licenseType")
    suspend fun getAllExamByLicenseType(licenseType: String): MutableList<Exam>

}
