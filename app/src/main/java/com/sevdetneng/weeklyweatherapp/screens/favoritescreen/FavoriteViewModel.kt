package com.sevdetneng.weeklyweatherapp.screens.favoritescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevdetneng.weeklyweatherapp.model.Favorite
import com.sevdetneng.weeklyweatherapp.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val weatherDbRepository: WeatherDbRepository) :
    ViewModel() {
    private val _favorites = MutableStateFlow<List<Favorite>>(emptyList())
    val favorites = _favorites.asStateFlow()

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            weatherDbRepository.getAllFavorites().distinctUntilChanged().collect { favorites ->
                if (favorites.isEmpty()) {
                    Log.d("Exc", "favorites null")
                } else {
                    _favorites.value = favorites
                }
            }
        }

    }

}