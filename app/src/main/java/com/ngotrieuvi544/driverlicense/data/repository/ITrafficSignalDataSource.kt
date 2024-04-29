package com.ngotrieuvi544.driverlicense.data.repository

import com.ngotrieuvi544.driverlicense.data.model.TrafficSigns
import com.ngotrieuvi544.driverlicense.utils.interfaces.IResponseListener

interface ITrafficSignalDataSource {
    interface Remote {
        fun getAllTrafficSignal(listener: IResponseListener<MutableList<TrafficSigns>>)
    }
}
