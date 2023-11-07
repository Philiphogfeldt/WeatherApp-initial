package com.plcoding.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.plcoding.weatherapp.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                val weatherViewModel: WeatherViewModel = viewModel()

                LaunchedEffect(key1 = true) {
                    weatherViewModel.loadWeatherInfo()
                }

                WeatherContent(weatherViewModel)
            }
        }
    }
}

@Composable
fun WeatherContent(weatherViewModel: WeatherViewModel) {
    val state = weatherViewModel.state
    if (state.isLoading) {
        CircularProgressIndicator()
    } else if (state.error != null) {
        Text(text = "Error: ${state.error}")
    } else {
        // Display latitude and longitude
        Text(text = "Latitude: ${weatherViewModel.latitude}")
        Text(text = "Longitude: ${weatherViewModel.longitude}")
    }
}

