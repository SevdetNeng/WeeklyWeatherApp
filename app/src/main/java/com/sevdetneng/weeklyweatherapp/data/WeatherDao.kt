package com.sevdetneng.weeklyweatherapp.data

import androidx.room.*
import com.sevdetneng.weeklyweatherapp.model.Favorite
import com.sevdetneng.weeklyweatherapp.model.WeatherUnit
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query("SELECT * FROM fav_tbl")
    fun getFavorites() : Flow<List<Favorite>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(fav : Favorite)

    @Delete
    suspend fun deleteFavorite(fav : Favorite)

    @Query("SELECT * FROM fav_tbl WHERE city = :city")
    suspend fun getFavoriteByCity(city : String) : Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit : WeatherUnit)

    @Query("DELETE FROM units_tbl")
    suspend fun deleteAllUnits()

    @Query("SELECT * FROM units_tbl")
    fun getUnits() : Flow<List<WeatherUnit>>
}