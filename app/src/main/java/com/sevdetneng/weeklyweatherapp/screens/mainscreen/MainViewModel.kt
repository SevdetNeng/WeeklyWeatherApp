package com.sevdetneng.weeklyweatherapp.screens.mainscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevdetneng.weeklyweatherapp.data.DataOrException
import com.sevdetneng.weeklyweatherapp.model.Favorite
import com.sevdetneng.weeklyweatherapp.model.Weather
import com.sevdetneng.weeklyweatherapp.model.WeatherUnit
import com.sevdetneng.weeklyweatherapp.repository.WeatherApiRepository
import com.sevdetneng.weeklyweatherapp.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherApiRepository,
                                        private val dbRepository: WeatherDbRepository) : ViewModel() {
    val data : MutableState<DataOrException<Weather,Boolean,Exception>>
            = mutableStateOf(DataOrException(null,true,null))

    private val _favorites = MutableStateFlow<List<Favorite>>(emptyList())
    val favorites = _favorites.asStateFlow()
    val isFavoriteState = mutableStateOf(false)
    val dayIndex = mutableStateOf(0)
    val unitType = mutableStateOf("metric")

    init {
        getDefaultUnit()
        getWeather("Istanbul", unitType.value)
        getFavorites()
    }


    fun getWeather(city : String,units : String){

        viewModelScope.launch {
            data.value = repository.getWeather(city,units)
        }
    }

    fun getFavorites(){
        viewModelScope.launch(Dispatchers.IO) {
            dbRepository.getAllFavorites().collect{
                _favorites.value = it
            }
        }
    }
    fun addFavorite(fav : Favorite){
        viewModelScope.launch{
            dbRepository.insertFavorite(fav)
        }
    }
    fun deleteFavorite(fav : Favorite){
        viewModelScope.launch{
            dbRepository.deleteFavorite(fav)
        }
    }
    fun toggleUnit(){
        viewModelScope.launch {
            if(unitType.value == "metric"){
                unitType.value = "imperial"
            }else{
                unitType.value = "metric"
            }
            dbRepository.deleteAllUnits()
            dbRepository.insertUnit(WeatherUnit(unitType.value))
        }
    }
    fun getDefaultUnit(){
        viewModelScope.launch{
            dbRepository.getAllUnits().collect{
                if(it.isNotEmpty())
                unitType.value = it[0].unit
            }
        }
    }
}