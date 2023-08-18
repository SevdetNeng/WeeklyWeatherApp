package com.sevdetneng.weeklyweatherapp.di

import android.content.Context
import androidx.room.Room
import com.sevdetneng.weeklyweatherapp.data.WeatherDao
import com.sevdetneng.weeklyweatherapp.data.WeatherDatabase
import com.sevdetneng.weeklyweatherapp.network.WeatherApi
import com.sevdetneng.weeklyweatherapp.repository.WeatherApiRepository
import com.sevdetneng.weeklyweatherapp.repository.WeatherDbRepository
import com.sevdetneng.weeklyweatherapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideWeatherApi() : WeatherApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context : Context) : WeatherDatabase {
        return Room.databaseBuilder(context,
            WeatherDatabase::class.java,
            "weather_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(weatherDb : WeatherDatabase) : WeatherDao = weatherDb.weatherDao


    @Singleton
    @Provides
    fun provideDbRepository(weatherDao : WeatherDao) : WeatherDbRepository{
        return WeatherDbRepository(weatherDao)
    }

    @Singleton
    @Provides
    fun provideApiRepository(weatherApi : WeatherApi) : WeatherApiRepository{
        return WeatherApiRepository(weatherApi)
    }
}