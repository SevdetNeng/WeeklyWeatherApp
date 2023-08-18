package com.sevdetneng.weeklyweatherapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sevdetneng.weeklyweatherapp.model.Favorite
import com.sevdetneng.weeklyweatherapp.model.WeatherUnit

@Database(version = 2, entities = [Favorite::class,WeatherUnit::class])
abstract class WeatherDatabase : RoomDatabase() {
    abstract val weatherDao : WeatherDao
}