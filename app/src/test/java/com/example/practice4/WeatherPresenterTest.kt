package com.example.practice4

import com.example.practice4.data.api.FakeWeatherApi
import com.example.practice4.data.model.Weather
import com.example.practice4.data.repository.WeatherRepository
import com.example.practice4.mvp.WeatherContract
import com.example.practice4.mvp.presenter.WeatherPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Test

class FakeView : WeatherContract.View {
    val events = mutableListOf<String>()

    override fun showLoading() { events += "loading" }
    override fun showWeather(weather: Weather) { events += "weather:${weather.city}" }
    override fun showError(message: String) { events += "error:$message" }
}
 
class WeatherPresenterTest {
 
    private val view = FakeView()
    private val presenter = WeatherPresenter(
        view = view,
        repository = WeatherRepository(FakeWeatherApi(latencyMs = 0)),
        // Unconfined: the coroutine runs right away, on the test thread
        scope = CoroutineScope(Dispatchers.Unconfined),
    )
 
    @Test
    fun known_city_shows_loading_then_weather() {
        presenter.onCitySelected("Tashkent")
        assertEquals(listOf("loading", "weather:Tashkent"), view.events)
    }
 
    @Test
    fun unknown_city_shows_loading_then_error() {
        view.events.clear()
        presenter.onCitySelected("Atlantis")
        assertEquals(listOf("loading", "error:No forecast for Atlantis"), view.events)
    }

    @Test
    fun two_requests_in_a_row_show_two_loading_events() {
        view.events.clear()
        presenter.onCitySelected("Tashkent")
        presenter.onCitySelected("Samarkand")
        assertEquals(listOf("loading", "weather:Tashkent", "loading", "weather:Samarkand"), view.events)
    }
}
