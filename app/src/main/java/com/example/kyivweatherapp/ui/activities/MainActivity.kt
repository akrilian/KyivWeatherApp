package com.example.kyivweatherapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kyivweatherapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        //val fragment = WeatherFragment() // создание экземпляра фрагмента
        //supportFragmentManager.beginTransaction().replace(R.id.placeHolder, fragment).commit() // замена содержимого контейнера фрагментом

        // добавляем фрагмент в контейнер

        //supportFragmentManager.beginTransaction().replace(R.id.placeHolder, WeatherFragment.newInstance()).commit()
    }
}