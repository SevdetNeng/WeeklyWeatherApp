package com.sevdetneng.weeklyweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sevdetneng.weeklyweatherapp.navigation.Navigation
import com.sevdetneng.weeklyweatherapp.screens.splashscreen.SplashScreen
import com.sevdetneng.weeklyweatherapp.ui.theme.WeeklyWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeeklyWeatherAppTheme {
                MainContent()

            }
        }
    }
}

@Composable
fun MainContent() {
    Column(verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.fillMaxSize()){
        Navigation()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeeklyWeatherAppTheme {
    }
}