package com.ngotrieuvi544.driverlicense.data.repository

import com.ngotrieuvi544.driverlicense.data.model.TipsHighScore
import com.ngotrieuvi544.driverlicense.utils.interfaces.IResponseListener

class TipsHighScoreRepository(
    private val remote: ITipsHighScoreDataSource.Remote,
) : ITipsHighScoreDataSource.Remote {

    override suspend fun callTipsHighScoreData(listener: IResponseListener<MutableList<TipsHighScore>>) =
        remote.callTipsHighScoreData(listener)
}
