package com.sevdetneng.weeklyweatherapp.repository

import android.util.Log
import com.sevdetneng.weeklyweatherapp.data.DataOrException
import com.sevdetneng.weeklyweatherapp.model.Weather
import com.sevdetneng.weeklyweatherapp.network.WeatherApi
import javax.inject.Inject

class WeatherApiRepository @Inject constructor(private val api: WeatherApi) {
    suspend fun getWeather(
        city: String,
        units: String
    ): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = city, units = units)
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
            return DataOrException(e = e)
        }
        return DataOrException(response)
    }
}