package com.ngotrieuvi544.driverlicense.utils.interfaces

import com.ngotrieuvi544.driverlicense.data.model.QuestionOptions

interface IBottomSheetListener {
    fun onNextQuestion(listener: IResponseListener<Int>)
    fun onPreviousQuestion(listener: IResponseListener<Int>)
    fun onClickMoveToPosition(index: Int, data: QuestionOptions)
}
