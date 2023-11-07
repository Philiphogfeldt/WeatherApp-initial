package com.plcoding.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.plcoding.weatherapp.presentation.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // Initialize the ViewModel
                val weatherViewModel: WeatherViewModel = viewModel()

                // Call your Composable function and pass in the latitude and longitude
                WeatherDataDisplay(
                    latitude = weatherViewModel.latitude,
                    longitude = weatherViewModel.longitude
                )
            }
        }
    }
}

@Composable
fun WeatherDataDisplay(latitude: Double?, longitude: Double?) {
    // You can format the string as you like
    Text(text = "Latitude: $latitude, Longitude: $longitude")
}
