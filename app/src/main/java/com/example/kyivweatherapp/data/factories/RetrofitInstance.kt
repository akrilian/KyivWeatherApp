package com.example.kyivweatherapp.data.factories

import com.example.kyivweatherapp.Api.WeatherApiEndPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private var baseurl = "http://api.weatherapi.com/"
    val api: WeatherApiEndPoint by lazy {
            Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApiEndPoint::class.java)
    }

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("http://api.weatherapi.com/v1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()


}