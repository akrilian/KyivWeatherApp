package com.example.kyivweatherapp.data.models

import com.google.gson.annotations.SerializedName

data class HourForecast(
    val time: String,
    @SerializedName("temp_c") val temp: String,
    val condition: Condition,

    )
