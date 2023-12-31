package com.sevdetneng.weeklyweatherapp.screens.splashscreen

import android.view.animation.OvershootInterpolator
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.sevdetneng.weeklyweatherapp.R
import com.sevdetneng.weeklyweatherapp.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val scale = remember {
        Animatable(0f)
    }

    // Splash Screen Animation
    LaunchedEffect(key1 = true, block = {
        scale.animateTo(targetValue = 1f,
            animationSpec = tween(durationMillis = 800,
                easing = {
                    OvershootInterpolator(2f)
                        .getInterpolation(it)
                }
            )
        )
        delay(2000)
        navController.navigate(Screens.MainScreen.name + "/new york") {
            popUpTo(Screens.SplashScreen.name) {
                inclusive = true
            }
        }
    })

    Surface(
        shape = CircleShape,
        modifier = Modifier
            .size(330.dp)
            .padding(15.dp)
            .scale(scale.value),
        border = BorderStroke(2.dp, Color.LightGray)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.sun), contentDescription = "Splash Image",
                modifier = Modifier.size(100.dp)
            )
            Text(
                "Weekly Weather",
                style = MaterialTheme.typography.h5
            )
        }
    }
}