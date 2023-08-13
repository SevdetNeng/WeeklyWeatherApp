package com.sevdetneng.weeklyweatherapp.repository

import com.sevdetneng.weeklyweatherapp.data.WeatherDao
import com.sevdetneng.weeklyweatherapp.model.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {
    suspend fun insertFavorite(fav : Favorite){
        weatherDao.insertFavorite(fav)
    }
    suspend fun deleteFavorite(fav : Favorite){
        weatherDao.deleteFavorite(fav)
    }
    fun getAllFavorites() : Flow<List<Favorite>> {
        return weatherDao.getFavorites()
    }
}