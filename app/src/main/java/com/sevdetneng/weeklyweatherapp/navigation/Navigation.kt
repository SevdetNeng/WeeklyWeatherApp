package com.sevdetneng.weeklyweatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sevdetneng.weeklyweatherapp.screens.favoritescreen.FavoriteScreen
import com.sevdetneng.weeklyweatherapp.screens.mainscreen.MainScreen
import com.sevdetneng.weeklyweatherapp.screens.searchscreen.SearchScreen
import com.sevdetneng.weeklyweatherapp.screens.splashscreen.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.SplashScreen.name ){
        composable(Screens.SplashScreen.name){
            SplashScreen(navController)
        }
        composable(Screens.MainScreen.name + "/{city}"){ navBackStack ->
            navBackStack.arguments?.getString("city").let { city->
                MainScreen(navController = navController,city = city!!)
            }
        }
        composable(Screens.SearchScreen.name){
            SearchScreen(navController = navController)
        }
        composable(Screens.FavoriteScreen.name){
            FavoriteScreen(navController = navController)
        }

    }
}