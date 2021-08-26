package com.example.theweathernow.view.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.theweathernow.model.dto.getweather.GetWeather
import com.example.theweathernow.model.retrofit.RetrofitClient
import com.example.theweathernow.view.Repository.GetWeatherRepo
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashViewModel : ViewModel() {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    private lateinit var weatherRepository: GetWeatherRepo
    val weatherLiveData = MutableLiveData<GetWeather?>()


    fun getWeather( lat : Int, lon : Int, appid : String, lang : String, units : String) {
        weatherRepository = GetWeatherRepo(RetrofitClient.getWeatherApi, lat, lon, appid, lang, units)
        scope.launch {
            val getWeatherResponse = weatherRepository.getWeather()
            weatherLiveData.postValue(getWeatherResponse)
        }
    }

    fun cancelRequests() = coroutineContext.cancel()
}

