package com.sevdetneng.weeklyweatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "units_tbl")
data class WeatherUnit(
    @PrimaryKey
    val unit : String
)
