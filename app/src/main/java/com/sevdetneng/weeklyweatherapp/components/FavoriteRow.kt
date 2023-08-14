package com.sevdetneng.weeklyweatherapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.sevdetneng.weeklyweatherapp.model.Favorite

@Composable
fun FavoriteRow(favorite : Favorite,onClick : () -> Unit){
    Card(shape = RoundedCornerShape(8.dp),
    modifier = Modifier.fillMaxWidth()
        .height(50.dp)
        .padding(top = 4.dp)
        .clickable {
            onClick()
        }){
        Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 2.dp)){
            Text(favorite.city)
            Text(favorite.country)
        }
    }
}