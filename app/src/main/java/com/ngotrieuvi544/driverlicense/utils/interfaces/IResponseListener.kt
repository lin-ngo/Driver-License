package com.ngotrieuvi544.driverlicense.utils.interfaces

interface IResponseListener<T> {
    fun onSuccess(data: T)
    fun onError(exception: Exception?)
}
