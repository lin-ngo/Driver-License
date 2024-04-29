package com.ngotrieuvi544.driverlicense.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ngotrieuvi544.driverlicense.data.repository.local.MotorbikeAppDatabase
import com.ngotrieuvi544.driverlicense.utils.constant.AppConstant
import com.ngotrieuvi544.driverlicense.utils.network.InternetConnection
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sharedPreferenceModule = module {
    single { provideSharedPreferences(androidContext()) }
}

val databaseModule = module {
    single { provideDatabase(androidContext()) }

    single { get<MotorbikeAppDatabase>().getExamDao() }
    single { get<MotorbikeAppDatabase>().getQuestionDao() }
    single { get<MotorbikeAppDatabase>().getStudyDao() }
    single { get<MotorbikeAppDatabase>().getWrongAnswerDao() }
}

val apiModule = module {
    single { Firebase.firestore }
}

val networkModule = module {
    single { provideNetworkObserver(androidContext()) }
}

fun provideNetworkObserver(context: Context) =
    InternetConnection(context)

fun provideDatabase(context: Context): MotorbikeAppDatabase =
    Room.databaseBuilder(
        context,
        MotorbikeAppDatabase::class.java,
        MotorbikeAppDatabase.DATABASE_NAME,
    ).allowMainThreadQueries().build()

fun provideSharedPreferences(context: Context): SharedPreferences =
    context.getSharedPreferences(AppConstant.SHARED_PREFERENCES_FILE_PATH,
        Context.MODE_PRIVATE)
