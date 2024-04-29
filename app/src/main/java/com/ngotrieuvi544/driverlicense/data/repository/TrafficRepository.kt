package com.ngotrieuvi544.driverlicense.data.repository

import com.ngotrieuvi544.driverlicense.data.model.TrafficSigns
import com.ngotrieuvi544.driverlicense.utils.interfaces.IResponseListener

class TrafficRepository(
    private val remote: ITrafficSignalDataSource.Remote,
) : ITrafficSignalDataSource.Remote {
    override fun getAllTrafficSignal(listener: IResponseListener<MutableList<TrafficSigns>>) =
        remote.getAllTrafficSignal(listener)
}
