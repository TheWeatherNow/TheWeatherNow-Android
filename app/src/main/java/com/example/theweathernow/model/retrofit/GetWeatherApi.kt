package com.example.theweathernow.model.retrofit

import com.example.theweathernow.model.dto.getweather.GetWeather
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetWeatherApi {

    //요청 예시
    //https://api.openweathermap.org/data/2.5/weather?lat=36&lon=129&appid=c06bcf4e4a98bf939f491630187005dc&lang=KR&units=metric

    @GET("weather")
    fun getWeather(
        //위치 x
        @Query("lat") lat : Int,
        //위치 y
        @Query("lon") lon : Int,
        //인증키
        @Query("appid") appid : String,
        //나라 언어 선택
        @Query("lang") lang : String,
        //측정 단위 (섭씨)
        @Query("units") units : String
    ) : Deferred<Response<GetWeather>>
}