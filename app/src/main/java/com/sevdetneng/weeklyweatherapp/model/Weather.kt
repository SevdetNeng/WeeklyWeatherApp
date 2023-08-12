package com.sevdetneng.weeklyweatherapp.model

data class Weather(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherDay>,
    val message: Double
)