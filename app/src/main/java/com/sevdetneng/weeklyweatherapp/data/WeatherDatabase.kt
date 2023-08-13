package com.sevdetneng.weeklyweatherapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sevdetneng.weeklyweatherapp.model.Favorite

@Database(version = 1, entities = [Favorite::class])
abstract class WeatherDatabase : RoomDatabase() {
    abstract val weatherDao : WeatherDao
}