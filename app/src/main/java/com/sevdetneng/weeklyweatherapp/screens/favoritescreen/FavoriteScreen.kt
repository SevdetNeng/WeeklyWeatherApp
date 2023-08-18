package com.sevdetneng.weeklyweatherapp.screens.favoritescreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        Surface(shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxSize()
                .padding(12.dp),
            color = Color.LightGray){
            LazyColumn(modifier = Modifier.padding(8.dp)){
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
}
