package com.sevdetneng.weeklyweatherapp.data

import androidx.room.*
import com.sevdetneng.weeklyweatherapp.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query("SELECT * FROM fav_tbl")
    fun getFavorites() : Flow<List<Favorite>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(fav : Favorite)
    @Delete
    suspend fun deleteFavorite(fav : Favorite)
}