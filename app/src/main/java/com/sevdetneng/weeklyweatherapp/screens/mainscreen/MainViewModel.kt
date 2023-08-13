package com.sevdetneng.weeklyweatherapp.screens.mainscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevdetneng.weeklyweatherapp.data.DataOrException
import com.sevdetneng.weeklyweatherapp.model.Weather
import com.sevdetneng.weeklyweatherapp.repository.WeatherApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherApiRepository) : ViewModel() {
    val data : MutableState<DataOrException<Weather,Boolean,Exception>>
    = mutableStateOf(DataOrException(null,true,null))

    val dayIndex = mutableStateOf(0)
    val unitType = mutableStateOf("metric")

    init {
        getWeather("Istanbul","Metric")
    }


    fun getWeather(city : String,units : String){
        viewModelScope.launch {
            data.value = repository.getWeather(city,units)
        }
    }
}