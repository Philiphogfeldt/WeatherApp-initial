package com.plcoding.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {

    @GET("v1/forecast?latitude=57.721&longitude=12.9401&current=weathercode&hourly=temperature_2m,relativehumidity_2m,weathercode,pressure_msl,windspeed_10m")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double,
        //@Query("current") current: String = "weathercode"
    ): WeatherDto
}