package com.example.practice4.mvp

import com.example.practice4.data.model.Weather

interface WeatherContract {
    interface View {
        fun showLoading()
        fun showWeather(weather: Weather)
        fun showError(message: String)
    }
}
