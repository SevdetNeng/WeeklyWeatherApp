package com.sevdetneng.weeklyweatherapp.repository


import com.sevdetneng.weeklyweatherapp.data.WeatherDao
import com.sevdetneng.weeklyweatherapp.model.Favorite
import com.sevdetneng.weeklyweatherapp.model.WeatherUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {
    suspend fun insertFavorite(fav : Favorite){
        weatherDao.insertFavorite(fav)
    }
    suspend fun deleteFavorite(fav : Favorite){
        weatherDao.deleteFavorite(fav)
    }
    fun getAllFavorites() : Flow<List<Favorite>> {
        return weatherDao.getFavorites().flowOn(Dispatchers.IO).conflate()
    }

    fun getAllUnits() : Flow<List<WeatherUnit>>{
        return weatherDao.getUnits()
    }
    suspend fun insertUnit(unit : WeatherUnit){
        weatherDao.insertUnit(unit)
    }
    suspend fun deleteAllUnits(){
        weatherDao.deleteAllUnits()
    }
}