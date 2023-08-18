package com.sevdetneng.weeklyweatherapp.screens.mainscreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import coil.compose.rememberImagePainter
import com.sevdetneng.weeklyweatherapp.R
import com.sevdetneng.weeklyweatherapp.components.ThisWeekRow
import com.sevdetneng.weeklyweatherapp.components.WeatherTopBar
import com.sevdetneng.weeklyweatherapp.model.Favorite
import com.sevdetneng.weeklyweatherapp.model.Weather

import com.sevdetneng.weeklyweatherapp.util.formatDate
import com.sevdetneng.weeklyweatherapp.util.formatDateTime
import com.sevdetneng.weeklyweatherapp.util.formatDecimals

@Composable
fun MainScreen(navController: NavController,city : String,
               mainViewModel : MainViewModel = hiltViewModel()){

    val unitType = mainViewModel.unitType
    mainViewModel.getWeather(city,unitType.value)
    val weatherData = mainViewModel.data.value.data
    val dayIndex = mainViewModel.dayIndex
    val favorites = mainViewModel.favorites.collectAsState().value
    val isFavorite = mainViewModel.isFavoriteState.value



    if(weatherData!=null){

        //isFavorite.value = favorites.contains(Favorite(weatherData.city.name,weatherData.city.country))
        mainViewModel.isFavoriteState.value = favorites.contains(Favorite(weatherData.city.name,weatherData.city.country))
        Log.d("Fav","is favorite = ${mainViewModel.isFavoriteState.value}")
        Scaffold(topBar = { WeatherTopBar(title = weatherData.city.name, country = weatherData.city.country,
            isMain = true, navController = navController,
            isFavorite = isFavorite,
            onFavoriteAdd = {
                mainViewModel.addFavorite(Favorite(weatherData.city.name,weatherData.city.country))
            },
            onFavoriteDelete = {
                mainViewModel.deleteFavorite(Favorite(weatherData.city.name,weatherData.city.country))
            }
        ) }) {
            Column(modifier = Modifier.fillMaxSize()){
                TodayInfo(weather = weatherData,dayIndex,unitType){
                    mainViewModel.toggleUnit()
                }
                Divider(modifier = Modifier.padding(horizontal = 12.dp))
                ThisWeekLazyColumn(weather = weatherData,dayIndex)
            }
        }
    }else{
        Scaffold(topBar = { WeatherTopBar(title = "City", country = "Country",
            isMain = false, navController = navController
        ){
            navController.popBackStack()
        } }) {
            Column(modifier = Modifier.fillMaxSize()){
                Text("City Not Found")
            }
        }
    }

}

@Composable
fun TodayInfo(weather : Weather,
              dayIndex : MutableState<Int>,
              unitState : MutableState<String>,
              onToggleUnit : () -> Unit

){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(modifier = Modifier.weight(1f)){

            }
            Text(formatDate(weather.list[dayIndex.value].dt),
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .weight(1f),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center)
            Row(horizontalArrangement = Arrangement.End,
                modifier = Modifier.weight(1f)){
                Surface(shape = RoundedCornerShape(4.dp),
                    color = Color.Magenta.copy(alpha = 0.4f),
                    modifier = Modifier.clickable {
                        onToggleUnit()
                    }) {
                    Text(text = if(unitState.value=="imperial") "F°" else "C°",
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                }
            }

        }

        Surface(shape = CircleShape,
            modifier = Modifier.size(200.dp),
            border = BorderStroke(1.dp, Color.Black),
            color = Color.LightGray

        ){
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()){
                ImageLoader(imageId = weather.list[dayIndex.value].weather[0].icon,
                    modifier = Modifier.size(80.dp))
                Text(formatDecimals(weather.list[dayIndex.value].temp.day)+"°",
                    style = MaterialTheme.typography.h4)
                Text(weather.list[dayIndex.value].weather[0].main,
                    style = MaterialTheme.typography.h5,
                    fontStyle = FontStyle.Italic)
            }
        }
        SunriseSunsetRow(weather = weather,dayIndex)
    }
}

@Composable
fun SunriseSunsetRow(weather: Weather,dayIndex : MutableState<Int>){
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)){
        Row(){
            Icon(painterResource(id = R.drawable.sunrise ) ,"Sunrise",
                modifier = Modifier.size(30.dp)
            )
            Text(formatDateTime(weather.list[dayIndex.value].sunrise))
        }
        Row(){
            Text(formatDateTime(weather.list[dayIndex.value].sunset))
            Icon(painterResource(id = R.drawable.sunset ) ,"Sunrise",
                modifier = Modifier.size(30.dp)
            )
        }

    }
}

@Composable
fun ThisWeekLazyColumn(weather : Weather,dayIndex: MutableState<Int>){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 8.dp, end = 8.dp, bottom = 4.dp, top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top){
        Text("This Week", style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        Surface(modifier = Modifier.fillMaxSize(),
            color = Color.LightGray,
            shape = RoundedCornerShape(8.dp)
        )
        {
            LazyColumn{
                itemsIndexed(weather.list){ index,day ->
                    ThisWeekRow(day = day,index ){
                        dayIndex.value = index
                    }
                }
            }
        }
    }
}

@Composable
fun ImageLoader(imageId : String,modifier: Modifier = Modifier){
    Image(rememberImagePainter("https://openweathermap.org/img/wn/$imageId.png"),
        contentDescription = "Image",
        modifier = modifier)
}