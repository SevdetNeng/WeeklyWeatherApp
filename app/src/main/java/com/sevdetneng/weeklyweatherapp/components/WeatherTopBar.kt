package com.sevdetneng.weeklyweatherapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sevdetneng.weeklyweatherapp.navigation.Screens

@Composable
fun WeatherTopBar(title : String,
                  country : String = "",
                  isMain : Boolean,
                  navController: NavController,
                  isFavorite : Boolean = false,
                  onFavoriteAdd : () -> Unit={},
                  onFavoriteDelete : () -> Unit = {},
                  onBackClick : () -> Unit = {}){
    val isExpanded = remember{ mutableStateOf(false) }
    if(isExpanded.value){
        WeatherDropdownMenu(navController = navController, expanded = isExpanded)
    }
    TopAppBar(title = {Text(text = if(country=="") title else "$title, $country")},
        backgroundColor = Color.Transparent,
        elevation = 4.dp,
        modifier = Modifier.padding(4.dp),
        actions = {
            if(isMain){
                IconButton(onClick = {
                    if(isFavorite){
                        onFavoriteDelete()
                    }else{
                        onFavoriteAdd()
                    }
                }){
                    Icon(imageVector = if(isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        "Favorite",
                    tint = Color.Red.copy(alpha = 0.4f))
                }
            }
            IconButton(onClick = {
                navController.navigate(Screens.SearchScreen.name)
            }) {
                Icon(imageVector = Icons.Default.Search,"Search")
            }
            IconButton(onClick = { isExpanded.value = !isExpanded.value }) {
                Icon(imageVector = Icons.Default.MoreVert,"More")
            }
        },
        navigationIcon = {
            if (!isMain){
                Icon(imageVector = Icons.Default.ArrowBack,"Back",
                    modifier = Modifier.clickable {
                        onBackClick()
                    }
                )
            }
        }

    )
}

@Composable
fun WeatherDropdownMenu(navController: NavController,expanded : MutableState<Boolean>){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.TopEnd)
        .absolutePadding(top = 45.dp, right = 40.dp)){
        DropdownMenu(expanded = expanded.value, onDismissRequest = {expanded.value = false},
            modifier = Modifier.width(140.dp)
        ) {

            DropdownMenuItem(onClick = {navController.navigate(Screens.AboutScreen.name)}) {

                Icon(imageVector = Icons.Default.Info,"Info")
                Text("About")
            }
            DropdownMenuItem(onClick = { navController.navigate(Screens.FavoriteScreen.name) }) {
                Icon(imageVector = Icons.Default.FavoriteBorder,"Info")
                Text("Favorites")
            }
        }
    }

}