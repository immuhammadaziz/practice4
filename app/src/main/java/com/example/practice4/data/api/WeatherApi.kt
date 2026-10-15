package com.example.practice4.data.api

import com.example.practice4.data.model.Weather

interface WeatherApi {
    suspend fun fetchWeather(city: String): Weather
}
