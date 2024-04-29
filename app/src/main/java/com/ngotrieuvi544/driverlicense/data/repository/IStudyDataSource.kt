package com.ngotrieuvi544.driverlicense.data.repository

import com.ngotrieuvi544.driverlicense.data.model.NewQuestion
import com.ngotrieuvi544.driverlicense.data.model.StudyCategory
import com.ngotrieuvi544.driverlicense.utils.interfaces.IResponseListener

interface IStudyDataSource {
    interface Local {
        suspend fun saveProgress(studyCategoryList: List<StudyCategory>)
        suspend fun getAllInfoStudyCategory(): List<StudyCategory>
    }

    interface Remote {
        suspend fun getListQuestion(listener: IResponseListener<MutableList<NewQuestion>>)
    }
}
