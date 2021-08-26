package com.example.theweathernow.view.activity.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.theweathernow.R
import com.example.theweathernow.base.BaseActivity
import com.example.theweathernow.model.dto.getweather.GetWeather
import com.example.theweathernow.utils.Retrofit
import com.example.theweathernow.view.activity.main.MainActivity

class SplashActivity : BaseActivity() {
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
            if (it != null) {
                successNext(it)
            }
        })
    }

    private fun successNext(response : GetWeather){
        val intent = Intent(this, MainActivity::class.java)
        //영어 오늘 날씨 한줄 설명
        intent.putExtra("weatherMain", response.weather.get(0).main)
        //오늘 온도 섭씨
        intent.putExtra("temp",response.main.temp.toString())
        //지역 영어 이름
        intent.putExtra("name",response.name)
        //날짜 타임스탬프
        intent.putExtra("dt",response.dt.toString())
        startActivity(intent)
        finish()
    }
}