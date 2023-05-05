package com.example.kyivweatherapp.data.models

import com.google.gson.annotations.SerializedName

data class Condition(
    val icon: String,
    @SerializedName("text") val currentCondition: String
)