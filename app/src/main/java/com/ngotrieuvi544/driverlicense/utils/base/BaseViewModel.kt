package com.ngotrieuvi544.driverlicense.utils.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngotrieuvi544.driverlicense.utils.extensions.convertSecondToMillisecond
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    protected val loading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = loading

    protected val exception = MutableLiveData<Exception?>()
    val hasException: LiveData<Exception?>
        get() = exception

    private val _isVisibleFinishExamButton = MutableLiveData(false)
    val isVisibleFinishExamButton: LiveData<Boolean>
        get() = _isVisibleFinishExamButton

    private val _isVisibleResetButton = MutableLiveData(false)
    val isVisibleResetButton: LiveData<Boolean>
        get() = _isVisibleResetButton

    private var _timeOutLoadingJob: Job? = null

    protected fun launchTask(
        onRequest: suspend CoroutineScope.() -> Unit = {},
    ) = viewModelScope.launch {
        showLoading()
        onRequest(this)
    }

    protected fun showLoading() {
        loading.value = true

        //Set timeout 7s
        _timeOutLoadingJob = viewModelScope.launch {
            delay(7L.convertSecondToMillisecond())
            loading.value = false
        }
    }

    protected fun hideLoading() {
        loading.value = false
        _timeOutLoadingJob?.cancel()
    }

    fun setVisibleFinishExamButton(isVisible: Boolean) {
        _isVisibleFinishExamButton.value = isVisible
    }

    fun setVisibleResetButton(isVisible: Boolean) {
        _isVisibleResetButton.value = isVisible
    }
}

