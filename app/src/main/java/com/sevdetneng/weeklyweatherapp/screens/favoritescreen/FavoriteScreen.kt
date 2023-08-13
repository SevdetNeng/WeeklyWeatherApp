package com.sevdetneng.weeklyweatherapp.screens.favoritescreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sevdetneng.weeklyweatherapp.components.WeatherTopBar

@Composable
fun FavoriteScreen(navController: NavController){
    val favoriteViewModel : FavoriteViewModel = hiltViewModel()
    val favorites = favoriteViewModel.favorites.collectAsState()
    Scaffold(topBar = { WeatherTopBar(title = "Favorites", isMain = false, navController = navController ){
        navController.popBackStack()
    } }) {
        LazyColumn{
            items(favorites.value){ favorite ->
                Text(favorite.city)
            }
        }
    }
}