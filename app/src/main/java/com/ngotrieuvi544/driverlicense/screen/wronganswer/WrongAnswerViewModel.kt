package com.ngotrieuvi544.driverlicense.screen.wronganswer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ngotrieuvi544.driverlicense.data.model.NewQuestion
import com.ngotrieuvi544.driverlicense.data.model.QuestionOptions
import com.ngotrieuvi544.driverlicense.data.model.WrongAnswer
import com.ngotrieuvi544.driverlicense.data.repository.WrongAnswerRepository
import com.ngotrieuvi544.driverlicense.utils.base.BaseViewModel
import com.ngotrieuvi544.driverlicense.utils.generateEmptyQuestionStateList
import com.ngotrieuvi544.driverlicense.utils.interfaces.IResponseListener
import com.ngotrieuvi544.driverlicense.utils.provideEmptyQuestionOption
import kotlinx.coroutines.launch

class WrongAnswerViewModel(
    private val wrongAnswerRepository: WrongAnswerRepository,
) : BaseViewModel() {

    private val _listAllQuestion = MutableLiveData<MutableList<NewQuestion>>()

    private val _listWrongAnswerQuestion = MutableLiveData<MutableList<NewQuestion>>()
    val listWrongAnswerQuestion: LiveData<MutableList<NewQuestion>>
        get() = _listWrongAnswerQuestion

    private val _listWrongAnswer = MutableLiveData<MutableList<WrongAnswer>>()
    val listWrongAnswer: LiveData<MutableList<WrongAnswer>>
        get() = _listWrongAnswer

    private val _listQuestionState = MutableLiveData<MutableList<QuestionOptions>>()
    val listQuestionOptions: LiveData<MutableList<QuestionOptions>>
        get() = _listQuestionState

    init {
        fetchData()
    }

    private fun fetchData() {
        launchTask {
            val data = wrongAnswerRepository.getAllWrongAnswerQuestion()

            if (data.isNotEmpty()) {
                _listWrongAnswer.postValue(data)
                wrongAnswerRepository.getAllListQuestion(
                    object : IResponseListener<MutableList<NewQuestion>> {
                        override fun onSuccess(data: MutableList<NewQuestion>) {
                            val wrongAnswerList = mutableListOf<NewQuestion>()
                            val listSelectedQuestionOptions = mutableListOf<QuestionOptions>()
                            _listAllQuestion.postValue(data)
                            _listWrongAnswer.value?.forEach { wrongAnswerQuestion ->
                                data.forEach {
                                    if (wrongAnswerQuestion.questionsID == it.id) {
                                        wrongAnswerList.add(it)
                                        return@forEach
                                    }
                                }
                                listSelectedQuestionOptions.add(wrongAnswerQuestion.lastSelectedState)
                            }

                            _listWrongAnswerQuestion.postValue(wrongAnswerList)
                            _listQuestionState.postValue(listSelectedQuestionOptions)
                            hideLoading()
                        }

                        override fun onError(exception: Exception?) {
                            this@WrongAnswerViewModel.exception.postValue(exception)
                            hideLoading()
                        }
                    }
                )
            }

            //hideLoading()
        }
    }

    fun updateNewDataFromDatabase(listener: IResponseListener<Boolean>) {
        launchTask {
            val data = wrongAnswerRepository.getAllWrongAnswerQuestion()
            _listWrongAnswer.postValue(data)

            val currentListQuestion = _listAllQuestion.value ?: mutableListOf()
            val newList = mutableListOf<NewQuestion>()

            currentListQuestion.forEach { question ->
                data.forEach {
                    if (it.questionsID == question.id) {
                        newList.add(question)
                        return@forEach
                    }
                }
            }

            _listWrongAnswerQuestion.postValue(newList)
            _listQuestionState.postValue(generateEmptyQuestionStateList(newList))
            hideLoading()

            listener.onSuccess(true)
        }
    }

    fun updateDataQuestionPos(questionsPosition: Int, item: QuestionOptions) {
        val list = _listQuestionState.value
        list?.set(questionsPosition, item)
        _listQuestionState.value = list ?: mutableListOf()
    }

    fun updateSelectedToDatabase(questionID: Int, item: QuestionOptions) {
        viewModelScope.launch {
            var wrongAnswer = wrongAnswerRepository.findWrongAnswerQuestionByID(questionID)
            wrongAnswer = wrongAnswer?.copy(
                lastSelectedState = item
            ) ?: WrongAnswer(
                questionID,
                System.currentTimeMillis(),
                item
            )

            wrongAnswerRepository.updateWrongAnswerQuestion(wrongAnswer)
        }
    }

    fun removeAllSelectedState() {
        viewModelScope.launch {
            val listWrongAnswer = wrongAnswerRepository.getAllWrongAnswerQuestion()
            listWrongAnswer.forEach {
                wrongAnswerRepository.updateWrongAnswerQuestion(
                    it.copy(
                        lastSelectedState = provideEmptyQuestionOption(it.questionsID)
                    )
                )
            }
        }
    }

//    fun getQuestionOptionSelectedByQuestionPosition(questionsPosition: Int): QuestionOptions? {
//        return if (questionsPosition != AppConstant.NONE_POSITION) {
//            _listQuestionState.value?.get(questionsPosition)
//        } else null
//    }
}
