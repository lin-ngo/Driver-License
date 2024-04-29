package com.ngotrieuvi544.driverlicense.data.repository

import com.ngotrieuvi544.driverlicense.data.model.NewQuestion
import com.ngotrieuvi544.driverlicense.data.model.WrongAnswer
import com.ngotrieuvi544.driverlicense.utils.interfaces.IResponseListener

interface IWrongAnswerDataSource {
    interface Local {
        suspend fun getAllWrongAnswerQuestion(): MutableList<WrongAnswer>
        suspend fun insertNewWrongAnswerQuestion(wrongAnswer: WrongAnswer)
        suspend fun updateWrongAnswerQuestion(wrongAnswer: WrongAnswer)
        suspend fun findWrongAnswerQuestionByID(id: Int): WrongAnswer?
    }

    interface Remote {
        suspend fun getAllListQuestion(listener: IResponseListener<MutableList<NewQuestion>>)
    }
}
