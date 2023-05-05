package com.example.kyivweatherapp.ForecastWeather

import android.app.Application
import androidx.lifecycle.*
import com.example.kyivweatherapp.data.factories.RetrofitInstance.retrofit
import com.example.kyivweatherapp.Api.WeatherApiCurrent
import com.example.kyivweatherapp.data.models.HourForecast
import kotlinx.coroutines.launch

class HourlyWeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val apiKey = "68d24a5a812243b6bff131750232404"
    private val weatherApiCurrent = retrofit.create(WeatherApiCurrent::class.java)

    private val _hourlyWeather = MutableLiveData<List<HourForecast>>()
    val hourlyWeather: LiveData<List<HourForecast>> = _hourlyWeather

    fun updateHourlyWeather(location: String) {
        viewModelScope.launch {
            val response = weatherApiCurrent.getCurrentWeather(apiKey, location)
            if (response.isSuccessful) {
                val forecast = response.body()?.forecast?.forecastday?.get(0)
                val hourlyWeather = forecast?.hour?.map { it } ?: emptyList()
                _hourlyWeather.postValue(hourlyWeather)
            } else {
                _hourlyWeather.postValue(emptyList())
            }
        }
    }
}
