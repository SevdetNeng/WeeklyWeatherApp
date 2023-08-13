package com.sevdetneng.weeklyweatherapp.screens.searchscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


class SearchViewModel : ViewModel() {
    val cityState : MutableState<String> = mutableStateOf("")
    val valid : MutableState<Boolean> = mutableStateOf(false)

    fun setCity(city : String){
        cityState.value = city
    }

}