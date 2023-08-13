package com.sevdetneng.weeklyweatherapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sevdetneng.weeklyweatherapp.navigation.Screens

@Composable
fun WeatherTopBar(title : String,country : String = "",isMain : Boolean,navController: NavController,onBackClick : () -> Unit = {}){
    TopAppBar(title = {Text(text = if(country=="") title else "$title, $country")},
        backgroundColor = Color.Transparent,
        elevation = 4.dp,
        modifier = Modifier.padding(4.dp),
        actions = {
            if(isMain){
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.FavoriteBorder,"Favorite")
                }
            }
            IconButton(onClick = {
                navController.navigate(Screens.SearchScreen.name)
            }) {
                Icon(imageVector = Icons.Default.Search,"Search")
            }
            IconButton(onClick = { /*TODO*/ }) {
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