package com.sevdetneng.weeklyweatherapp.screens.favoritescreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sevdetneng.weeklyweatherapp.components.FavoriteRow
import com.sevdetneng.weeklyweatherapp.components.WeatherTopBar
import com.sevdetneng.weeklyweatherapp.navigation.Screens

@Composable
fun FavoriteScreen(navController: NavController){
    val favoriteViewModel : FavoriteViewModel = hiltViewModel()
    val favorites = favoriteViewModel.favorites.collectAsState()
    Scaffold(topBar = { WeatherTopBar(title = "Favorites", isMain = false, navController = navController ){
        navController.popBackStack()
    } }) {
        LazyColumn(modifier = Modifier.padding(12.dp)){
            items(favorites.value){ favorite ->
                FavoriteRow(favorite = favorite){
                    navController.navigate(Screens.MainScreen.name+"/${favorite.city}"){
                        popUpTo(Screens.FavoriteScreen.name){
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}