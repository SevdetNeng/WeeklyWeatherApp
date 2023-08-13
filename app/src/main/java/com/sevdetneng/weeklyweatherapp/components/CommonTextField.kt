package com.sevdetneng.weeklyweatherapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun CommonTextField(city : MutableState<String>,
                    label : String,
                    imeAction: ImeAction = ImeAction.Next,
                    keyboardType: KeyboardType = KeyboardType.Text,
                    onAction: KeyboardActions = KeyboardActions.Default

){
    OutlinedTextField(value = city.value, onValueChange = {
        city.value = it
    },
        label = {Text(label)},
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType,
            imeAction = imeAction),
        keyboardActions = onAction,
        modifier = Modifier.fillMaxWidth()
    )
}