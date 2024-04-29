package com.ngotrieuvi544.driverlicense.data.repository.local.study

import com.ngotrieuvi544.driverlicense.data.model.StudyCategory
import com.ngotrieuvi544.driverlicense.data.repository.IStudyDataSource

class StudyLocalDataSource(
    private val studyDao: StudyDao,
) : IStudyDataSource.Local {

    override suspend fun saveProgress(studyCategoryList: List<StudyCategory>) =
        studyDao.saveNewStudyCategory(studyCategoryList)

    override suspend fun getAllInfoStudyCategory(): List<StudyCategory> =
        studyDao.getAllInfoStudyCategory()

}

