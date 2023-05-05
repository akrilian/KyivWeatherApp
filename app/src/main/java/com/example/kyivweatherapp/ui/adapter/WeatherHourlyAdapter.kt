package com.example.kyivweatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.kyivweatherapp.data.models.HourForecast
import com.example.kyivweatherapp.R
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso


class WeatherHourlyAdapter : ListAdapter<HourForecast, WeatherHourlyAdapter.ViewHolder>(HourForecastDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val HourForecast = getItem(position)
        viewHolder.tempTextView.text = HourForecast.temp
        viewHolder.timeTextView.text = HourForecast.time
        Picasso.get().load("https:" + HourForecast.condition.icon).into(viewHolder.imageView)
        viewHolder.conditionTextView.text = HourForecast.condition.currentCondition
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timeTextView = itemView.findViewById<TextView>(R.id.tvDate)
        val tempTextView = itemView.findViewById<TextView>(R.id.tvTemp)
        val imageView = itemView.findViewById<ImageView>(R.id.im)
        val conditionTextView = itemView.findViewById<TextView>(R.id.tvCondition)
    }

    class HourForecastDiffCallback : DiffUtil.ItemCallback<HourForecast>() {
        override fun areItemsTheSame(oldItem: HourForecast, newItem: HourForecast): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HourForecast, newItem: HourForecast): Boolean {
            return oldItem == newItem
        }
    }
}