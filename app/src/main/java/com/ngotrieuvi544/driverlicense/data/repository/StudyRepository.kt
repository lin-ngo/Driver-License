package com.ngotrieuvi544.driverlicense.data.repository

import com.ngotrieuvi544.driverlicense.data.model.NewQuestion
import com.ngotrieuvi544.driverlicense.data.model.StudyCategory
import com.ngotrieuvi544.driverlicense.utils.interfaces.IResponseListener

class StudyRepository(
    private val local: IStudyDataSource.Local,
    private val remote: IStudyDataSource.Remote,
) : IStudyDataSource.Local, IStudyDataSource.Remote {

    override suspend fun saveProgress(studyCategoryList: List<StudyCategory>) =
        local.saveProgress(studyCategoryList)

    override suspend fun getAllInfoStudyCategory() = local.getAllInfoStudyCategory()
    
    override suspend fun getListQuestion(listener: IResponseListener<MutableList<NewQuestion>>) {
        remote.getListQuestion(listener)
    }

}
