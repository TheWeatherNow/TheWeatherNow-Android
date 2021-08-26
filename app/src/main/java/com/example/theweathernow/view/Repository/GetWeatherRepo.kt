package com.example.theweathernow.view.Repository

import com.example.theweathernow.model.dto.getweather.GetWeather
import com.example.theweathernow.model.retrofit.GetWeatherApi

class GetWeatherRepo(
    private val apiInterface: GetWeatherApi,
    private val lat: Int,
    private val lon: Int,
    private val appid: String,
    private val lang: String,
    private val units: String
) :
    BaseRepository() {
    //get latest news using safe api call
    suspend fun getWeather(): GetWeather? {
        return safeApiCall(
            //await the result of deferred type
            call = { apiInterface.getWeather(lat, lon,appid,lang,units).await() },
            error = "Error fetching news" //convert to mutable list
        )
    }
}

