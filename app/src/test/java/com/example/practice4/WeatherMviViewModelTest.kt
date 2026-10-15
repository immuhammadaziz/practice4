package com.example.practice4

import com.example.practice4.data.api.FakeWeatherApi
import com.example.practice4.data.repository.WeatherRepository
import com.example.practice4.mvi.model.WeatherIntent
import com.example.practice4.mvi.viewmodel.WeatherMviViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherMviViewModelTest {
 
    @Before
    fun setUp() {
        // viewModelScope runs on Dispatchers.Main, so replace it for the test
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }
 
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
 
    private fun newViewModel() =
        WeatherMviViewModel(WeatherRepository(FakeWeatherApi(latencyMs = 0)))
 
    @Test
    fun selecting_a_known_city_ends_with_weather_and_no_loading() {
        val viewModel = newViewModel()
 
        viewModel.onIntent(WeatherIntent.SelectCity("Bukhara"))
 
        val state = viewModel.state.value
        assertFalse(state.isLoading)
        assertEquals("Bukhara", state.weather?.city)
        assertNull(state.error)
    }
 
    @Test
    fun selecting_an_unknown_city_ends_with_error() {
        val viewModel = newViewModel()
 
        viewModel.onIntent(WeatherIntent.SelectCity("Atlantis"))
 
        val state = viewModel.state.value
        assertFalse(state.isLoading)
        assertNull(state.weather)
        assertEquals("No forecast for Atlantis", state.error)
    }

    @Test
    fun dismissing_the_error_clears_it_and_keeps_the_old_weather() {
        val viewModel = newViewModel()
 
        viewModel.onIntent(WeatherIntent.SelectCity("Bukhara"))
        assertEquals("Bukhara", viewModel.state.value.weather?.city)

        viewModel.onIntent(WeatherIntent.SelectCity("Atlantis"))
        assertEquals("No forecast for Atlantis", viewModel.state.value.error)
        assertEquals("Bukhara", viewModel.state.value.weather?.city) // keeps old weather

        viewModel.onIntent(WeatherIntent.DismissError)
        assertNull(viewModel.state.value.error)
        assertEquals("Bukhara", viewModel.state.value.weather?.city)
    }
}
