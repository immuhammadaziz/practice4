package com.example.practice4.mvc.model

import com.example.practice4.data.model.Weather
import com.example.practice4.data.repository.WeatherRepository

class WeatherModel(private val repository: WeatherRepository) {
 
    var isLoading: Boolean = false
        private set
    var weather: Weather? = null
        private set
    var error: String? = null
        private set
 
    fun startLoading() {
        isLoading = true
        error = null
    }
 
    suspend fun load(city: String) {
        repository.getWeather(city)
            .onSuccess { weather = it }
            .onFailure { error = it.message ?: "Unknown error" }
        isLoading = false
    }
}
