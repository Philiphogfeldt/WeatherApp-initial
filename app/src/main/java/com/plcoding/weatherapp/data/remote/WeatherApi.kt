package com.plcoding.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {

    @GET("https://api.open-meteo.com/v1/forecast?latitude=57.721&longitude=12.9401&hourly=temperature_2m")
    suspend fun  getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ):WeatherDto
}