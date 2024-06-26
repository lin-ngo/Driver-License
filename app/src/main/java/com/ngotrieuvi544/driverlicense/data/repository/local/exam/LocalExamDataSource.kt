package com.ngotrieuvi544.driverlicense.data.repository.local.exam

import com.ngotrieuvi544.driverlicense.data.model.Exam
import com.ngotrieuvi544.driverlicense.data.repository.IExamDataSource

class LocalExamDataSource(
    private val examDao: ExamDao,
) : IExamDataSource.Local {

    override suspend fun insertNewExam(exam: Exam) = examDao.insertNewExam(exam)

    override suspend fun updateExam(exam: Exam) = examDao.updateExam(exam)
    override suspend fun getAllExamByLicenseType(licenseType: String)
        = examDao.getAllExamByLicenseType(licenseType)

    override suspend fun deleteExam(exam: Exam) = examDao.deleteExam(exam)

    override suspend fun getAllExam(): MutableList<Exam> = examDao.getAllExam()

    override suspend fun getDetailExamByID(id: Int): Exam = examDao.getDetailExamByID(id)

}
