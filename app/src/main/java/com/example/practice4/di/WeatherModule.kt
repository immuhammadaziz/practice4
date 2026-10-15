package com.example.practice4.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.practice4.data.api.FakeWeatherApi
import com.example.practice4.data.repository.WeatherRepository
import com.example.practice4.mvi.viewmodel.WeatherMviViewModel
import com.example.practice4.mvvm.viewmodel.WeatherViewModel

object WeatherModule {
    // One shared Model layer for all four patterns.
    // 3 seconds of fake latency leaves time to rotate the device in the experiments.
    val repository = WeatherRepository(FakeWeatherApi(latencyMs = 3_000))

    val weatherViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
        initializer { WeatherViewModel(repository) }
    }

    val weatherMviViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
        initializer { WeatherMviViewModel(repository) }
    }
}
