package com.plcoding.weatherapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.plcoding.weatherapp.domain.location.LocationTracker
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.domain.util.Resource
import com.plcoding.weatherapp.domain.weather.WeatherData
import com.plcoding.weatherapp.domain.weather.WeatherType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
): ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    var latitude by mutableStateOf<Double?>(null)
    var longitude by mutableStateOf<Double?>(null)




    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                latitude = location.latitude
                longitude = location.longitude

                when (val result = repository.getWeatherData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        state = state.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )
                        var currentWeatherType = result.data?.currentWeatherData?.weatherType
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't find any location, Please try again."
                )
            }
        }
    }

    fun searchWeatherByCoordinates(inputLat: Double, inputLong: Double) {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)

            when (val result = repository.getWeatherData(inputLat, inputLong)) {
                is Resource.Success -> {
                    state = state.copy(
                        weatherInfo = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        weatherInfo = null,
                        isLoading = false,
                        error = result.message ?: "An unknown error occurred"
                    )
                }
            }
        }
    }
}

