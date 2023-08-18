package com.sevdetneng.weeklyweatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sevdetneng.weeklyweatherapp.screens.aboutscreen.AboutScreen
import com.sevdetneng.weeklyweatherapp.screens.favoritescreen.FavoriteScreen
import com.sevdetneng.weeklyweatherapp.screens.mainscreen.MainScreen
import com.sevdetneng.weeklyweatherapp.screens.mainscreen.MainViewModel
import com.sevdetneng.weeklyweatherapp.screens.searchscreen.SearchScreen
import com.sevdetneng.weeklyweatherapp.screens.splashscreen.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val mainViewModel : MainViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = Screens.SplashScreen.name ){
        composable(Screens.SplashScreen.name){
            SplashScreen(navController)
        }
        composable(Screens.MainScreen.name + "/{city}"){ navBackStack ->
            navBackStack.arguments?.getString("city").let { city->
                MainScreen(navController = navController,city = city!!,
                    mainViewModel = mainViewModel
                )
            }
        }
        composable(Screens.SearchScreen.name){
            SearchScreen(navController = navController)
        }
        composable(Screens.FavoriteScreen.name){
            FavoriteScreen(navController = navController)
        }
        composable(Screens.AboutScreen.name){
            AboutScreen(navController = navController)
        }

    }
}