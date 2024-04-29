package com.ngotrieuvi544.driverlicense.data.repository

import com.ngotrieuvi544.driverlicense.data.model.Exam
import com.ngotrieuvi544.driverlicense.data.model.NewQuestion
import com.ngotrieuvi544.driverlicense.utils.interfaces.IResponseListener

interface IExamDataSource {
    interface Local {
        suspend fun insertNewExam(exam: Exam)
        suspend fun deleteExam(exam: Exam)
        suspend fun getAllExam(): MutableList<Exam>
        suspend fun getDetailExamByID(id: Int): Exam
        suspend fun updateExam(exam: Exam)
        suspend fun getAllExamByLicenseType(licenseType: String) : MutableList<Exam>
    }

    interface Remote {
        suspend fun getListQuestion(listener: IResponseListener<MutableList<NewQuestion>>)
    }
}
