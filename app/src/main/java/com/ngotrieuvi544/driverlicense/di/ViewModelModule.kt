package com.ngotrieuvi544.driverlicense.di

import com.ngotrieuvi544.driverlicense.screen.changelicensetype.ChangeLicenseTypeViewModel
import com.ngotrieuvi544.driverlicense.screen.exam.ExamViewModel
import com.ngotrieuvi544.driverlicense.screen.instruction.InstructionViewModel
import com.ngotrieuvi544.driverlicense.screen.settings.SettingsViewModel
import com.ngotrieuvi544.driverlicense.screen.splash.SplashViewModel
import com.ngotrieuvi544.driverlicense.screen.study.StudyViewModel
import com.ngotrieuvi544.driverlicense.screen.tiphighscores.TipsHighScoreViewModel
import com.ngotrieuvi544.driverlicense.screen.trafficsign.TrafficSignViewModel
import com.ngotrieuvi544.driverlicense.screen.wronganswer.WrongAnswerViewModel
import com.ngotrieuvi544.driverlicense.utils.base.BaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { BaseViewModel() }
    viewModel { SplashViewModel() }
    viewModel { TipsHighScoreViewModel(get()) }
    viewModel { ExamViewModel(get(), get(), get()) }
    viewModel { StudyViewModel(get(),get()) }
    viewModel { WrongAnswerViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { TrafficSignViewModel(get()) }
    viewModel { InstructionViewModel(get()) }
    viewModel { ChangeLicenseTypeViewModel(get()) }
}
