package com.sevdetneng.weeklyweatherapp.data

data class DataOrException<T,Boolean,E : Exception>(
    val data : T? = null,
    val isLoading : Boolean? = null,
    val e : E? = null
)
