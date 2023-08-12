package com.sevdetneng.weeklyweatherapp.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WeatherTopBar(title : String,isMain : Boolean){
    TopAppBar(title = {Text(title)},
        backgroundColor = Color.Transparent,
        elevation = 4.dp,
        modifier = Modifier.padding(4.dp),
        actions = {
            if(isMain){
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.FavoriteBorder,"Favorite")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Search,"Search")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.MoreVert,"More")
                }

            }
        }
    )
}