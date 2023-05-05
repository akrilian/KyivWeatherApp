package com.example.kyivweatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kyivweatherapp.ForecastWeather.HourlyWeatherViewModel
import com.example.kyivweatherapp.R
import com.example.kyivweatherapp.ui.adapter.WeatherHourlyAdapter
import com.example.kyivweatherapp.ui.viewmodels.SharedViewModel

class WeatherHourlyFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var hourlyWeatherViewModel: HourlyWeatherViewModel
    private lateinit var hourlyWeatherAdapter: WeatherHourlyAdapter
    private lateinit var location: String

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        location = "Kiev"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather_hourly, container, false)
        recyclerView = view.findViewById(R.id.rv)
        hourlyWeatherAdapter = WeatherHourlyAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = hourlyWeatherAdapter
        }

        sharedViewModel.locationLiveData.observe(viewLifecycleOwner, Observer { location ->
            hourlyWeatherViewModel.updateHourlyWeather(location)
        })
        //val location = arguments?.getString("location")
        //location = arguments?.getString("location") ?: "Kiev"

        hourlyWeatherViewModel = ViewModelProvider(this).get(HourlyWeatherViewModel::class.java)
        hourlyWeatherViewModel.updateHourlyWeather(location)

        hourlyWeatherViewModel.hourlyWeather.observe(
            viewLifecycleOwner,
            Observer { hourlyWeather ->
                hourlyWeather?.let {
                    hourlyWeatherAdapter.submitList(hourlyWeather)
                }
            })

        return view
    }

    //fun updateWeather(location: String) {
      //  hourlyWeatherViewModel.updateHourlyWeather(location)
    //}
}
