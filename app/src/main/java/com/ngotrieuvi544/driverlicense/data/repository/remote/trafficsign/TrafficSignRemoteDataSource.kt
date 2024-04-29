package com.ngotrieuvi544.driverlicense.data.repository.remote.trafficsign

import com.google.firebase.firestore.FirebaseFirestore
import com.ngotrieuvi544.driverlicense.data.model.TrafficSigns
import com.ngotrieuvi544.driverlicense.data.repository.ITrafficSignalDataSource
import com.ngotrieuvi544.driverlicense.utils.constant.AppConstant
import com.ngotrieuvi544.driverlicense.utils.interfaces.IResponseListener

class TrafficSignRemoteDataSource(
    private val fireStoreDB: FirebaseFirestore,
) : ITrafficSignalDataSource.Remote {

    private val trafficSignsCollections by lazy {
        fireStoreDB.collection(AppConstant.TRAFFIC_SIGN_COLLECTION)
    }

    override fun getAllTrafficSignal(listener: IResponseListener<MutableList<TrafficSigns>>) {
        trafficSignsCollections.get().addOnCompleteListener { tasks ->
            if (tasks.isSuccessful) {
                val listTrafficSign = mutableListOf<TrafficSigns>()

                tasks.result.documents.forEach {
                    it.toObject(TrafficSigns::class.java)?.let { notNullObject ->
                        listTrafficSign.add(notNullObject)
                    }
                }

                listener.onSuccess(listTrafficSign)
            } else {
                listener.onError(tasks.exception)
            }
        }
    }
}
