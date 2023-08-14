package com.sevdetneng.weeklyweatherapp.screens.aboutscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.sevdetneng.weeklyweatherapp.components.WeatherTopBar

@Composable
fun AboutScreen(navController: NavController){
    Scaffold(topBar = { WeatherTopBar(title = "About", isMain = false, navController = navController ){
        navController.popBackStack()
    } }) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Text("Weekly Weather App", style = MaterialTheme.typography.h5)
            Text("made by @SevdetNeng", style = MaterialTheme.typography.subtitle1)
        }
    }

}