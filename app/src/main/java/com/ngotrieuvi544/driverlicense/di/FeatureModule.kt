package com.ngotrieuvi544.driverlicense.di

import com.ngotrieuvi544.driverlicense.data.repository.ExamRepository
import com.ngotrieuvi544.driverlicense.data.repository.IExamDataSource
import com.ngotrieuvi544.driverlicense.data.repository.IStudyDataSource
import com.ngotrieuvi544.driverlicense.data.repository.ITipsHighScoreDataSource
import com.ngotrieuvi544.driverlicense.data.repository.ITrafficSignalDataSource
import com.ngotrieuvi544.driverlicense.data.repository.IWrongAnswerDataSource
import com.ngotrieuvi544.driverlicense.data.repository.StudyRepository
import com.ngotrieuvi544.driverlicense.data.repository.TipsHighScoreRepository
import com.ngotrieuvi544.driverlicense.data.repository.TrafficRepository
import com.ngotrieuvi544.driverlicense.data.repository.WrongAnswerRepository
import com.ngotrieuvi544.driverlicense.data.repository.local.exam.LocalExamDataSource
import com.ngotrieuvi544.driverlicense.data.repository.local.study.StudyLocalDataSource
import com.ngotrieuvi544.driverlicense.data.repository.local.wronganswer.WrongAnswerLocalDataSource
import com.ngotrieuvi544.driverlicense.data.repository.remote.exam.RemoteExamDataSource
import com.ngotrieuvi544.driverlicense.data.repository.remote.study.StudyRemoteDataSource
import com.ngotrieuvi544.driverlicense.data.repository.remote.tipshighscore.RemoteTipsHighScoreDataSource
import com.ngotrieuvi544.driverlicense.data.repository.remote.trafficsign.TrafficSignRemoteDataSource
import com.ngotrieuvi544.driverlicense.data.repository.remote.wronganswer.WrongAnswerRemoteDataSource
import org.koin.dsl.module

val repositoryModule = module {
    single { TipsHighScoreRepository(get()) }
    single { ExamRepository(get(), get()) }
    single { StudyRepository(get(), get()) }
    single { WrongAnswerRepository(get(), get()) }
    single { TrafficRepository(get()) }
}

val tipsHighScoreDataSourceModule = module {
    single<ITipsHighScoreDataSource.Remote> { RemoteTipsHighScoreDataSource(get()) }
}

val examDataSourceModule = module {
    single<IExamDataSource.Local> { LocalExamDataSource(get()) }
    single<IExamDataSource.Remote> { RemoteExamDataSource(get()) }
}

val studyDataSourceModule = module {
    single<IStudyDataSource.Local> { StudyLocalDataSource(get()) }
    single<IStudyDataSource.Remote> { StudyRemoteDataSource(get()) }
}

val wrongAnswerDataSourceModule = module {
    single<IWrongAnswerDataSource.Local> { WrongAnswerLocalDataSource(get()) }
    single<IWrongAnswerDataSource.Remote> { WrongAnswerRemoteDataSource(get()) }
}

val trafficSignDataSourceModule = module {
    single<ITrafficSignalDataSource.Remote> { TrafficSignRemoteDataSource(get()) }
}
