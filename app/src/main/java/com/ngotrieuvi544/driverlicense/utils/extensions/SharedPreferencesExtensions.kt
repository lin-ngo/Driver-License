package com.ngotrieuvi544.driverlicense.utils.extensions

import android.content.SharedPreferences
import com.ngotrieuvi544.driverlicense.data.model.LicenseType
import com.ngotrieuvi544.driverlicense.utils.constant.AppConstant

fun SharedPreferences.setCurrentLicenseType(currentLicenseType: String) {
    this.edit().putString(AppConstant.CURRENT_LICENSE_TYPE, currentLicenseType).apply()
}

fun SharedPreferences.getCurrentLicenseType() : LicenseType {
    val licenseType = this.getString(AppConstant.CURRENT_LICENSE_TYPE, LicenseType.A1.type)
    return enumValues<LicenseType>().first { it.type == licenseType }
}

fun SharedPreferences.isCurrentDarkMode()
    = this.getBoolean(AppConstant.DARK_MODE, false)

