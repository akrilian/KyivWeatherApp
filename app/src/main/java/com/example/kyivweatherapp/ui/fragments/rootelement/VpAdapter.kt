package com.example.kyivweatherapp.ui.fragments.rootelement

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kyivweatherapp.ui.fragments.WeatherFragment
import com.example.kyivweatherapp.ui.fragments.WeatherHourlyFragment

/*
class VpAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val weatherFragment: WeatherFragment = WeatherFragment()
    private val weatherHourlyFragment: WeatherHourlyFragment = WeatherHourlyFragment()

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> weatherFragment
            1 -> weatherHourlyFragment
            else -> throw IllegalArgumentException("Invalid adapter position")
        }
    }

    fun updateWeather(location: String) {
        weatherFragment.viewModel.updateWeather(location)
        weatherHourlyFragment.updateWeather(location)
    }
}*/


class VpAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> WeatherFragment()
            else-> WeatherHourlyFragment()
        }
    }
}

