package com.example.kyivweatherapp.ui.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kyivweatherapp.data.factories.RetrofitInstance.retrofit
import com.example.kyivweatherapp.Api.WeatherApiCurrent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val apiKey = "68d24a5a812243b6bff131750232404"
    private val weatherApiCurrent = retrofit.create(WeatherApiCurrent::class.java)

    val temperature = MutableLiveData<String>()
    val city = MutableLiveData<String>()
    val condition = MutableLiveData<String>()
    val imageUrl = MutableLiveData<String>()


    fun updateWeather(location: String) {
        viewModelScope.launch {
            try {
                val response = weatherApiCurrent.getCurrentWeather(apiKey, location)
                withContext(Dispatchers.Main) {
                    temperature.value = response.body()?.current?.temp_c?.toFloat()?.toInt().toString() + "°C"
                    city.value = response.body()?.location?.name.toString()
                    condition.value = response.body()?.current?.condition?.currentCondition.toString()
                    imageUrl.value = "https:" + response.body()?.current?.condition?.icon.toString()

                    //


                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(getApplication(), "${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun updateWeatherByLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                val response = weatherApiCurrent.getCurrentWeather(apiKey, "$latitude,$longitude")
                withContext(Dispatchers.Main) {
                    temperature.value = response.body()?.current?.temp_c?.toFloat()?.toInt().toString() + "°C"
                    city.value = response.body()?.location?.name.toString()
                    condition.value = response.body()?.current?.condition?.currentCondition.toString()
                    imageUrl.value = "https:" + response.body()?.current?.condition?.icon.toString()

                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(getApplication(), "${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }



}





/*
class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val apiKey = "68d24a5a812243b6bff131750232404"
    private val weatherApiCurrent = retrofit.create(WeatherApiCurrent::class.java)

    val temperature = MutableLiveData<String>()
    val city = MutableLiveData<String>()
    val condition = MutableLiveData<String>()

    fun updateWeather(location: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = location?.let { weatherApiCurrent.getCurrentWeather(apiKey, it) }
                withContext(Dispatchers.Main) {
                    temperature.value = response?.body()?.current?.temp_c?.toFloat()?.toInt().toString() + "°C"
                    city.value = response?.body()?.location?.name.toString()
                    condition.value = response?.body()?.current?.condition?.currentCondition.toString()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(getApplication(), "${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
*/