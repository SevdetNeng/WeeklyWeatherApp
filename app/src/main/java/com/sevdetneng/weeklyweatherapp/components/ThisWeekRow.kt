package com.sevdetneng.weeklyweatherapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.sevdetneng.weeklyweatherapp.model.WeatherDay
import com.sevdetneng.weeklyweatherapp.util.formatDate
import com.sevdetneng.weeklyweatherapp.util.formatDecimals

@Composable
fun ThisWeekRow(day : WeatherDay){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(75.dp)
        .padding(start = 4.dp, end = 4.dp, top = 4.dp),
    shape = RoundedCornerShape(bottomStart = 32.dp, topStart = 32.dp, topEnd = 4.dp, bottomEnd = 32.dp),
        backgroundColor = Color.White
    ){
        Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 2.dp)){
            Text(formatDate(day.dt).substringBefore(","))
            Image(rememberImagePainter("https://openweathermap.org/img/wn/${day.weather[0].icon}.png"),"Image",
            modifier = Modifier.size(80.dp))
            Card(shape = RoundedCornerShape(16.dp),
                backgroundColor = Color(0xFFFFBF00)
            ){
                Text(day.weather[0].description, modifier = Modifier.padding(1.dp),
                style = MaterialTheme.typography.caption)
            }
            Row(){
                Text(formatDecimals(day.temp.max)+"°" , color = Color.Blue)
                Text(formatDecimals(day.temp.min)+"°" , color = Color.LightGray)

            }
        }
    }
}