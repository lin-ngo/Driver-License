package com.ngotrieuvi544.driverlicense.utils

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.ngotrieuvi544.driverlicense.data.model.LicenseType
import com.ngotrieuvi544.driverlicense.di.apiModule
import com.ngotrieuvi544.driverlicense.di.databaseModule
import com.ngotrieuvi544.driverlicense.di.examDataSourceModule
import com.ngotrieuvi544.driverlicense.di.networkModule
import com.ngotrieuvi544.driverlicense.di.repositoryModule
import com.ngotrieuvi544.driverlicense.di.sharedPreferenceModule
import com.ngotrieuvi544.driverlicense.di.studyDataSourceModule
import com.ngotrieuvi544.driverlicense.di.tipsHighScoreDataSourceModule
import com.ngotrieuvi544.driverlicense.di.trafficSignDataSourceModule
import com.ngotrieuvi544.driverlicense.di.viewModelModule
import com.ngotrieuvi544.driverlicense.di.wrongAnswerDataSourceModule
import com.ngotrieuvi544.driverlicense.utils.constant.AppConstant
import com.ngotrieuvi544.driverlicense.utils.extensions.isCurrentDarkMode
import com.ngotrieuvi544.driverlicense.utils.extensions.setCurrentLicenseType
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MotorbikeApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MotorbikeApplication)

            modules(
                sharedPreferenceModule,
                databaseModule,
                apiModule,
                networkModule,
                repositoryModule,
                viewModelModule,
                tipsHighScoreDataSourceModule,
                examDataSourceModule,
                studyDataSourceModule,
                wrongAnswerDataSourceModule,
                trafficSignDataSourceModule,
            )
        }

        val sharedPreferences = inject<SharedPreferences>().value

        if (sharedPreferences.getString(AppConstant.CURRENT_LICENSE_TYPE, AppConstant.EMPTY_DATA)?.isEmpty() == true) {
            sharedPreferences.setCurrentLicenseType(LicenseType.A1.type)
        }

        if (sharedPreferences.isCurrentDarkMode()) {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        }
    }
}
