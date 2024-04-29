package com.ngotrieuvi544.driverlicense.screen.tiphighscores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ngotrieuvi544.driverlicense.data.model.TipsHighScore
import com.ngotrieuvi544.driverlicense.data.repository.TipsHighScoreRepository
import com.ngotrieuvi544.driverlicense.utils.base.BaseViewModel
import com.ngotrieuvi544.driverlicense.utils.interfaces.IResponseListener

class TipsHighScoreViewModel(
    private val repository: TipsHighScoreRepository,
) : BaseViewModel() {
    private val _listTipsHighScore = MutableLiveData<MutableList<TipsHighScore>>()

    val listTipsHighScore: LiveData<MutableList<TipsHighScore>>
        get() = _listTipsHighScore

    init {
        fetchData()
    }

    private fun fetchData() {
        launchTask {
            repository.callTipsHighScoreData(
                object : IResponseListener<MutableList<TipsHighScore>> {
                    override fun onSuccess(data: MutableList<TipsHighScore>) {
                        _listTipsHighScore.postValue(data)
                        hideLoading()
                    }

                    override fun onError(exception: Exception?) {
                        this@TipsHighScoreViewModel.exception.postValue(exception)
                        hideLoading()
                    }
                }
            )
        }
    }

}
