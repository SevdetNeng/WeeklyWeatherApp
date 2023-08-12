package com.sevdetneng.weeklyweatherapp.screens.mainscreen

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sevdetneng.weeklyweatherapp.components.WeatherTopBar

@Composable
fun MainScreen(navController: NavController){
    val mainViewModel : MainViewModel = hiltViewModel()
    Scaffold(topBar = { WeatherTopBar(title = "Main Screen", isMain = true) }) {
        Text(mainViewModel.data.value.data?.city.toString())
    }
}