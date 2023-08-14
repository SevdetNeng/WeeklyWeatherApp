package com.sevdetneng.weeklyweatherapp.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sevdetneng.weeklyweatherapp.model.Favorite
import com.sevdetneng.weeklyweatherapp.navigation.Screens
import com.sevdetneng.weeklyweatherapp.screens.favoritescreen.FavoriteViewModel

@Composable
fun WeatherTopBar(title : String,
                  country : String = "",
                  isMain : Boolean,
                  navController: NavController,
                  favorites : List<Favorite> = emptyList(),
                  onBackClick : () -> Unit = {}){
    val favViewModel : FavoriteViewModel = hiltViewModel()
    val isFavorite = favorites.contains(Favorite(title,country))
    val isFavoriteState = rememberSaveable{ mutableStateOf(isFavorite) }
    isFavoriteState.value = isFavorite
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
                if(isFavoriteState.value){
                    IconButton(onClick = {
                        Log.d("Fav","Exist")
                        isFavoriteState.value = false
                        favViewModel.deleteFavorite(Favorite(title,country))

                    }) {
                        Icon(imageVector = Icons.Default.Favorite,"Favorite Exists",
                            tint = Color.Red.copy(alpha = 0.4f)
                        )
                    }
                }else{
                    IconButton(onClick = {
                        Log.d("Fav","Not Exist")
                        isFavoriteState.value = true
                        favViewModel.insertFavorite(Favorite(title,country))

                    }) {
                        Icon(imageVector = Icons.Default.FavoriteBorder,"Favorite Not Exists",
                            tint = Color.Red.copy(alpha = 0.4f)
                        )
                    }
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