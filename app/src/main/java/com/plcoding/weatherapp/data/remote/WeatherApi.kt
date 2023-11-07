package com.plcoding.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {

    @GET("https://api.open-meteo.com/v1/forecast?")
    suspend fun  getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ):WeatherDto
}