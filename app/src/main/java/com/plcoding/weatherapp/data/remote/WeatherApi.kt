package com.plcoding.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {

    @GET("https://api.open-meteo.com/v1/forecast?latitude&longitude&current=weathercode&hourly=temperature_2m,relativehumidity_2m,weathercode,pressure_msl,windspeed_10m")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double,
        @Query("current") current: String = "weathercode",
        @Query("hourly") hourly: String = "temperature_2m,relativehumidity_2m,weathercode,pressure_msl,windspeed_10m"
    ): WeatherDto
}