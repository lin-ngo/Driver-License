package com.ngotrieuvi544.driverlicense.data.repository.local.wronganswer

import com.ngotrieuvi544.driverlicense.data.model.WrongAnswer
import com.ngotrieuvi544.driverlicense.data.repository.IWrongAnswerDataSource

class WrongAnswerLocalDataSource(
    private val wrongAnswerDao: WrongAnswerDao,
) : IWrongAnswerDataSource.Local {

    override suspend fun getAllWrongAnswerQuestion(): MutableList<WrongAnswer> =
        wrongAnswerDao.getAllWrongAnswerQuestion()

    override suspend fun insertNewWrongAnswerQuestion(wrongAnswer: WrongAnswer) =
        wrongAnswerDao.insertNewWrongAnswerQuestion(wrongAnswer)

    override suspend fun updateWrongAnswerQuestion(wrongAnswer: WrongAnswer) =
        wrongAnswerDao.updateWrongAnswerQuestion(wrongAnswer)

    override suspend fun findWrongAnswerQuestionByID(id: Int) =
        wrongAnswerDao.findWrongAnswerQuestionByID(id)
}
