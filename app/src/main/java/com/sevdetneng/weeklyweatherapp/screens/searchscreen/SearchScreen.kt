package com.sevdetneng.weeklyweatherapp.screens.searchscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions

import androidx.compose.material.Scaffold

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sevdetneng.weeklyweatherapp.components.CommonTextField
import com.sevdetneng.weeklyweatherapp.components.WeatherTopBar
import com.sevdetneng.weeklyweatherapp.navigation.Screens

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(navController: NavController) {
    val searchViewModel: SearchViewModel = viewModel()
    val cityName = searchViewModel.cityState
    val valid = searchViewModel.valid
    val keyboard = LocalSoftwareKeyboardController.current
    valid.value = cityName.value.isNotBlank()
    Scaffold(topBar = {
        WeatherTopBar(
            title = "Search",
            isMain = false, navController = navController
        ) {
            navController.popBackStack()
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            CommonTextField(city = cityName,
                label = "City",
                onAction = KeyboardActions {
                    if (valid.value) {
                        navController.navigate(Screens.MainScreen.name + "/${cityName.value}") {
                            popUpTo(Screens.SearchScreen.name) {
                                inclusive = true
                            }
                        }
                        cityName.value = ""
                        keyboard?.hide()
                    } else {
                        return@KeyboardActions
                    }
                }
            )
        }
    }

}