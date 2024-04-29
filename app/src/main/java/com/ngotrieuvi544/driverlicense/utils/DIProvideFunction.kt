package com.ngotrieuvi544.driverlicense.utils

import android.content.Context
import androidx.room.Room
import com.ngotrieuvi544.driverlicense.data.repository.local.MotorbikeAppDatabase

fun provideDatabase(context: Context) =
    Room.databaseBuilder(
        context,
        MotorbikeAppDatabase::class.java,
        MotorbikeAppDatabase.DATABASE_NAME,
    ).build()
