package com.ngotrieuvi544.driverlicense.data.repository.remote.study

import com.google.firebase.firestore.FirebaseFirestore
import com.ngotrieuvi544.driverlicense.data.model.NewQuestion
import com.ngotrieuvi544.driverlicense.data.repository.IStudyDataSource
import com.ngotrieuvi544.driverlicense.utils.constant.AppConstant
import com.ngotrieuvi544.driverlicense.utils.interfaces.IResponseListener

class StudyRemoteDataSource(
    private val fireStoreDB: FirebaseFirestore,
) : IStudyDataSource.Remote {

    private val questionCollections by lazy {
        fireStoreDB.collection(AppConstant.NEW_QUESTION_COLLECTION)
    }

    override suspend fun getListQuestion(listener: IResponseListener<MutableList<NewQuestion>>) {
        questionCollections.get().addOnCompleteListener { tasks ->
            if (tasks.isSuccessful) {
                val listQuestions = mutableListOf<NewQuestion>()

                tasks.result.documents.forEach {
                    it.toObject(NewQuestion::class.java)?.let { notNullObject ->
                        listQuestions.add(notNullObject)
                    }
                }

                listQuestions.sortBy { it.id }
                listener.onSuccess(listQuestions)
            } else {
                listener.onError(tasks.exception)
            }
        }
    }
}
