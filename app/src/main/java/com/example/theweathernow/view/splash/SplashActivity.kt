package com.example.theweathernow.view.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.theweathernow.R
import com.example.theweathernow.utils.Retrofit

class SplashActivity : AppCompatActivity() {
    private lateinit var splashViewModel: SplashViewModel
    private lateinit var splashViewModelFactory: SplashViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initViewModel()
        observeViewModel()
    }


    private fun initViewModel() {
        splashViewModelFactory = SplashViewModelFactory()
        splashViewModel = ViewModelProvider(
            this, splashViewModelFactory
        ).get(SplashViewModel::class.java)
        splashViewModel.getWeather(35, 126, Retrofit.API_KEY, "KR", "metric")

    }

    private fun observeViewModel() {
        splashViewModel.weatherLiveData.observe(this, Observer {
            Log.d("로그", "$it")
        })
    }
}