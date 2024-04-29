package com.ngotrieuvi544.driverlicense.screen.settings

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ngotrieuvi544.driverlicense.utils.base.BaseViewModel
import com.ngotrieuvi544.driverlicense.utils.constant.AppConstant
import com.ngotrieuvi544.driverlicense.utils.extensions.isCurrentDarkMode

class SettingsViewModel(
    private val sharedPreferences: SharedPreferences,
) : BaseViewModel() {

    private val _isDarkModeOn = MutableLiveData<Boolean>()

    init {
        _isDarkModeOn.postValue(sharedPreferences.isCurrentDarkMode())
    }

    val isDarkModeOn: LiveData<Boolean>
        get() = _isDarkModeOn

    fun turnOnDarkMode() {
        _isDarkModeOn.value = true
    }

    fun turnOffDarkMode() {
        _isDarkModeOn.value = false
    }

    fun saveDarkModeState() =
        sharedPreferences.edit()
            .putBoolean(AppConstant.DARK_MODE, _isDarkModeOn.value ?: false)
            .apply()
}
