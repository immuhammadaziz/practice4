package com.example.practice4.mvc.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.practice4.di.WeatherModule
import com.example.practice4.mvc.controller.WeatherController
import com.example.practice4.mvc.model.WeatherModel
import com.example.practice4.ui.WeatherScreen

class MvcActivity : ComponentActivity(), WeatherView {
 
    private val model = WeatherModel(WeatherModule.repository)
    private val controller = WeatherController(model, view = this)
    private var status by mutableStateOf("Pick a city")
 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                WeatherScreen(status = status, onCitySelected = controller::onCitySelected)
            }
        }
    }
 
    /**
     * Why does render() take no parameters?
     * Because in classic MVC, the View has a reference to the Model and directly pulls/reads
     * the state from the Model when notified that a change occurred.
     * What would change if the Controller passed the Weather into the View instead?
     * If data were passed directly, the View would become more dependent on the Controller/arguments
     * and less autonomous in pulling state from the Model, moving closer towards MVP or push-based models.
     */
    override fun render() {
        status = when {
            model.isLoading -> "Loading…"
            model.error != null -> "Error: ${model.error}"
            model.weather != null -> {
                val w = model.weather!!
                "${w.city}: ${w.temperatureC}°C, ${w.condition}"
            }
            else -> "Pick a city"
        }
    }
}
