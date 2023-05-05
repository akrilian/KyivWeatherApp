package com.example.kyivweatherapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val locationLiveData = MutableLiveData<String>()
} //для передачі локації з пошуку