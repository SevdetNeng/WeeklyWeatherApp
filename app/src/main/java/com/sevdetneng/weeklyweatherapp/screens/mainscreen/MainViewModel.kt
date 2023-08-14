package com.sevdetneng.weeklyweatherapp.screens.mainscreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevdetneng.weeklyweatherapp.data.DataOrException
import com.sevdetneng.weeklyweatherapp.model.Favorite
import com.sevdetneng.weeklyweatherapp.model.Weather
import com.sevdetneng.weeklyweatherapp.repository.WeatherApiRepository
import com.sevdetneng.weeklyweatherapp.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherApiRepository,
    private val dbRepository: WeatherDbRepository) : ViewModel() {
    val data : MutableState<DataOrException<Weather,Boolean,Exception>>
    = mutableStateOf(DataOrException(null,true,null))

    val _favorites : MutableState<List<Favorite>> = mutableStateOf(emptyList())


    val dayIndex = mutableStateOf(0)
    val unitType = mutableStateOf("metric")

    init {
        getWeather("Istanbul","Metric")
        getFavorites()
    }


    fun getWeather(city : String,units : String){
        viewModelScope.launch {
            data.value = repository.getWeather(city,units)
        }
    }
    fun getFavorites(){
        viewModelScope.launch {
            dbRepository.getAllFavorites().distinctUntilChanged().collect{
                if(it.isEmpty()){
                    Log.d("Exc","Empty Fav")
                }else{
                    _favorites.value = it
                }
            }
        }
    }
}