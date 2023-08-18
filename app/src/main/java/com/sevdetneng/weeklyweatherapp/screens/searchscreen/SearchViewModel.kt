package com.sevdetneng.weeklyweatherapp.screens.searchscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel



class SearchViewModel : ViewModel() {
    val cityState: MutableState<String> = mutableStateOf("")
    val valid: MutableState<Boolean> = mutableStateOf(false)

}