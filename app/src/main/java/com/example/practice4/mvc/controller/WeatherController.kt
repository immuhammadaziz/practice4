package com.example.practice4.mvc.controller

import com.example.practice4.mvc.model.WeatherModel
import com.example.practice4.mvc.view.WeatherView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class WeatherController(
    private val model: WeatherModel,
    private val view: WeatherView,
    private val scope: CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.Main),
) {
 
    fun onCitySelected(city: String) {
        scope.launch {
            // TODO 1: put the Model into the loading state, then ask the View to render
            model.startLoading()
            view.render()
            // TODO 2: let the Model load `city` (it suspends until the data arrives)
            model.load(city)
            // TODO 3: ask the View to render again
            view.render()
        }
    }
}
