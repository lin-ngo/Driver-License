package com.ngotrieuvi544.driverlicense.data.repository

import com.ngotrieuvi544.driverlicense.data.model.TipsHighScore
import com.ngotrieuvi544.driverlicense.utils.interfaces.IResponseListener

interface ITipsHighScoreDataSource {
    interface Remote {
        suspend fun callTipsHighScoreData(listener: IResponseListener<MutableList<TipsHighScore>>)
    }
}
