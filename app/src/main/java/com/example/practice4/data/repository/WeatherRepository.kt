package com.example.practice4.data.repository

import com.example.practice4.data.api.WeatherApi
import com.example.practice4.data.model.Weather
import kotlin.coroutines.cancellation.CancellationException

class WeatherRepository(private val api: WeatherApi) {
 
    suspend fun getWeather(city: String): Result<Weather> {
        return try {
            val weather = api.fetchWeather(city)
            Result.success(weather)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
