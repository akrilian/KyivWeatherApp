package com.example.kyivweatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kyivweatherapp.ui.viewmodels.WeatherViewModel
import com.example.kyivweatherapp.R
import com.example.kyivweatherapp.ui.viewmodels.SharedViewModel
import com.squareup.picasso.Picasso

class WeatherFragment : Fragment() {

    private lateinit var temp: TextView
    private lateinit var city: TextView
    private lateinit var condition: TextView
    private lateinit var weatherPic: ImageView


    private lateinit var search: androidx.appcompat.widget.SearchView
    lateinit var viewModel: WeatherViewModel


    private lateinit var sharedViewModel: SharedViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        temp = view.findViewById(R.id.temp)
        city = view.findViewById(R.id.city)
        condition = view.findViewById(R.id.condition)
        weatherPic = view.findViewById(R.id.weatherpic)
        search = view.findViewById(R.id.search)


        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        viewModel.updateWeatherByLocation(50.450001, 30.523333) //за зaмовчуванням локація києва

        viewModel.temperature.observe(viewLifecycleOwner, Observer { temperature ->
            temperature?.let {
                temp.text = it
            }
        })

        viewModel.city.observe(viewLifecycleOwner, Observer { city ->
            city?.let {
                this.city.text = it
            }
        })

        viewModel.condition.observe(viewLifecycleOwner, Observer { condition ->
            condition?.let {
                this.condition.text = it
            }
        })

        viewModel.imageUrl.observe(viewLifecycleOwner, Observer { imageUrl ->
            imageUrl?.let {
                Picasso.get().load(it).into(weatherPic)
            }
        })

        /* viewModel.currentWeather.observe(viewLifecycleOwner, Observer { weatherResponse ->
             weatherResponse?.let {
                 temp.text = it.current.temp_c?.toFloat()?.toInt().toString() + "°C"
                 city.text = it.location.name.toString()
                 condition.text = it.current.condition.currentCondition.toString()
                 Picasso.get().load("https:" + it.current.condition.icon).into(weatherPic)
             }
         })*/


        search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(location: String?): Boolean {
                //Log.d(TAG, "onQueryTextSubmit: $location")
                if (location != null) {
                    viewModel.updateWeather(location)

                    sharedViewModel.locationLiveData.value = location
                    return true
                     // для обнови в WeatherHourlyFragment з переходом на 2 фрагмент одразу
                    //val action = WeatherFragmentDirections.actionWeatherFragmentToWeatherHourlyFragment(location)
                    //findNavController().navigate(action)
                    }
                search.setQuery("", false)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        return view

    }
    }


/*
class WeatherFragment : Fragment() {

    private lateinit var temp: TextView
    private lateinit var city: TextView
    private lateinit var condition: TextView
    private lateinit var weatherPic: ImageView
    private lateinit var search: androidx.appcompat.widget.SearchView
    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        temp = view.findViewById(R.id.temp)
        city = view.findViewById(R.id.city)
        condition = view.findViewById(R.id.condition)
        weatherPic = view.findViewById(R.id.weatherpic)
        search = view.findViewById(R.id.search)

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        viewModel.updateWeatherByLocation(50.450001, 30.523333) //за зaмовчуванням локація києва

        viewModel.temperature.observe(viewLifecycleOwner, Observer { temperature ->
            temperature?.let {
                temp.text = it
            }
        })

        viewModel.city.observe(viewLifecycleOwner, Observer { city ->
            city?.let {
                this.city.text = it
            }
        })

        viewModel.condition.observe(viewLifecycleOwner, Observer { condition ->
            condition?.let {
                this.condition.text = it
            }
        })

        viewModel.imageUrl.observe(viewLifecycleOwner, Observer { imageUrl ->
            imageUrl?.let {
                Picasso.get().load(it).into(weatherPic)
            }
        })

        /* viewModel.currentWeather.observe(viewLifecycleOwner, Observer { weatherResponse ->
             weatherResponse?.let {
                 temp.text = it.current.temp_c?.toFloat()?.toInt().toString() + "°C"
                 city.text = it.location.name.toString()
                 condition.text = it.current.condition.currentCondition.toString()
                 Picasso.get().load("https:" + it.current.condition.icon).into(weatherPic)
             }
         })*/

        search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(location: String?): Boolean {
                //Log.d(TAG, "onQueryTextSubmit: $location")
                if (location != null) {
                    viewModel.updateWeather(location)
                }
                search.setQuery("", false)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        return view
    }*/