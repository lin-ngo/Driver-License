package com.ngotrieuvi544.driverlicense.screen.trafficsign

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ngotrieuvi544.driverlicense.data.model.TrafficSigns
import com.ngotrieuvi544.driverlicense.data.repository.TrafficRepository
import com.ngotrieuvi544.driverlicense.utils.base.BaseViewModel
import com.ngotrieuvi544.driverlicense.utils.extensions.processDoubleQuotes
import com.ngotrieuvi544.driverlicense.utils.extensions.processEndline
import com.ngotrieuvi544.driverlicense.utils.interfaces.IResponseListener

class TrafficSignViewModel(
    private val trafficRepository: TrafficRepository,
) : BaseViewModel() {

    private val _listTrafficSign = MutableLiveData<MutableList<TrafficSigns>>()

    val listTrafficSign: LiveData<MutableList<TrafficSigns>>
        get() = _listTrafficSign

    init {
        getTrafficSignListFromRemote()
    }

    private fun getTrafficSignListFromRemote() {
        launchTask {
            trafficRepository.getAllTrafficSignal(
                object : IResponseListener<MutableList<TrafficSigns>> {
                    override fun onSuccess(data: MutableList<TrafficSigns>) {
                        data.forEach {
                            it.title = it.title.processDoubleQuotes()
                            it.description = it.description.processEndline()
                        }
                        _listTrafficSign.postValue(data)
                        hideLoading()
                    }

                    override fun onError(exception: Exception?) {
                        this@TrafficSignViewModel.exception.postValue(exception)
                        hideLoading()
                    }
                }
            )
        }
    }

    fun getListByCategory(category: String) =
        _listTrafficSign.value
            ?.filter { return@filter it.category.lowercase() == category.lowercase() }
            ?.toMutableList()
}
