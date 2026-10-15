package com.example.practice4.mvi.model

import com.example.practice4.data.model.Weather

data class WeatherState(
    val isLoading: Boolean = false,
    val weather: Weather? = null,
    val error: String? = null,
)
 
sealed interface WeatherIntent {
    data class SelectCity(val city: String) : WeatherIntent
    data object DismissError : WeatherIntent
}
