package com.plcoding.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {

    @GET("v1/forecast")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double,
        @Query("current") current: String = "weathercode"
    ): WeatherDto
}