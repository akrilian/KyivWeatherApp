package com.example.kyivweatherapp.Api
import com.example.kyivweatherapp.data.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiEndPoint {
    @GET("v1/forecast.json?key=68d24a5a812243b6bff131750232404&q=London&days=1&aqi=no&alerts=no")
    suspend fun getWeather(): WeatherResponse
}

interface WeatherApiCurrent {
    @GET("forecast.json")
    suspend fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") days: String = "3",
        @Query("aqi") aqi: String = "no"
    ): Response<WeatherResponse>
}
/*
interface WeatherApiForecast {
    @GET("forecast.json")
    suspend fun getWeatherForecast(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") days: String = "1",
        @Query("aqi") aqi: String = "no"
    ): Response<WeatherResponseForecast>
}
 */
