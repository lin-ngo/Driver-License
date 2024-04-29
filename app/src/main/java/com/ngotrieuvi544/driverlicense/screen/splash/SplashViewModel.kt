package com.ngotrieuvi544.driverlicense.screen.splash

import androidx.lifecycle.MutableLiveData
import com.ngotrieuvi544.driverlicense.utils.base.BaseViewModel
import com.ngotrieuvi544.driverlicense.utils.constant.AppConstant
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class SplashViewModel : BaseViewModel() {

    private val _loadingText = MutableLiveData<String>()
    val loadingText: MutableLiveData<String>
        get() = _loadingText

    private val _isLoadingDone = MutableLiveData<Boolean>()
    val isLoadingDone: MutableLiveData<Boolean>
        get() = _isLoadingDone

    private val loadingFlow = flow {
        for (i in MIN_LOOP..MAX_LOOP) {
            emit(LOADING + ICON.repeat(i))
            delay(AppConstant.TIME_TASK_DELAYED)
        }
    }

    init {
        loadingData()
    }

    private fun loadingData() {
        launchTask() {
            loadingFlow.collect {
                _loadingText.postValue(it)
            }

            _isLoadingDone.postValue(true)
        }
    }

    companion object {
        const val LOADING = "Đang tải"
        const val ICON = "."
        const val MIN_LOOP = 1
        const val MAX_LOOP = 3
    }
}
