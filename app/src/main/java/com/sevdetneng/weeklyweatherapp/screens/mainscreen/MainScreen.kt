package com.sevdetneng.weeklyweatherapp.screens.mainscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.sevdetneng.weeklyweatherapp.R
import com.sevdetneng.weeklyweatherapp.components.ThisWeekRow
import com.sevdetneng.weeklyweatherapp.components.WeatherTopBar
import com.sevdetneng.weeklyweatherapp.model.Weather
import com.sevdetneng.weeklyweatherapp.model.WeatherDay
import com.sevdetneng.weeklyweatherapp.util.formatDate
import com.sevdetneng.weeklyweatherapp.util.formatDateTime
import com.sevdetneng.weeklyweatherapp.util.formatDecimals

@Composable
fun MainScreen(navController: NavController,city : String){
    val mainViewModel : MainViewModel = hiltViewModel()
    mainViewModel.getWeather(city,"metric")
    val weatherData = mainViewModel.data.value.data

    if(weatherData!=null){
        Scaffold(topBar = { WeatherTopBar(title = "Main Screen", isMain = true) }) {
            Column(modifier = Modifier.fillMaxSize()){
                TodayInfo(weather = weatherData)
                Divider(modifier = Modifier.padding(horizontal = 12.dp))
                ThisWeekLazyColumn(weather = weatherData)
            }

        }
    }

}

@Composable
fun TodayInfo(weather : Weather){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){  Text(formatDate(weather.list[0].dt),
    modifier = Modifier.padding(bottom = 4.dp),
    style = MaterialTheme.typography.caption,
    fontWeight = FontWeight.Bold)
        Surface(shape = CircleShape,
            modifier = Modifier.size(200.dp),
            border = BorderStroke(1.dp, Color.Black),
            color = Color.LightGray

        ){
            Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()){
                ImageLoader(imageId = weather.list[0].weather[0].icon,
                modifier = Modifier.size(80.dp))
                Text(formatDecimals(weather.list[0].temp.day)+"°",
                style = MaterialTheme.typography.h4)
                Text(weather.list[0].weather[0].main,
                style = MaterialTheme.typography.h5,
                fontStyle = FontStyle.Italic)
            }
        }
        SunriseSunsetRow(weather = weather)
    }
}

@Composable
fun SunriseSunsetRow(weather: Weather){
    Row(horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
        .fillMaxWidth()
        .padding(top = 4.dp)){
        Row(){
            Icon(painterResource(id = R.drawable.sunrise ) ,"Sunrise",
            modifier = Modifier.size(30.dp)
            )
            Text(formatDateTime(weather.list[0].sunrise))
        }
        Row(){
            Text(formatDateTime(weather.list[0].sunset))
            Icon(painterResource(id = R.drawable.sunset ) ,"Sunrise",
                modifier = Modifier.size(30.dp)
            )
        }

    }
}

@Composable
fun ThisWeekLazyColumn(weather : Weather){
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
                items(weather.list){ day ->
                    ThisWeekRow(day = day )
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