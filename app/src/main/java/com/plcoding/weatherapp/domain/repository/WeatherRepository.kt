package com.plcoding.weatherapp.domain.repository

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double)
}