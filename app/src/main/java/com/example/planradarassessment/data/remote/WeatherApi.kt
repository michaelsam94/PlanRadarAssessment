package com.example.planradarassessment.data.remote

import com.example.planradarassessment.data.remote.dto.CityDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {


    @GET("/data/2.5/weather")
    fun getCity(@Query("q") city: String,@Query("appid") apiKey: String) : Single<CityDto>

}