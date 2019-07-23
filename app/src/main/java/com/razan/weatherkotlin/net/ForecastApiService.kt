package com.razan.weatherkotlin.net

import com.razan.weatherkotlin.model.ForecastResponse
import com.razan.weatherkotlin.utils.ENDPOINT_WEATHER_STATE
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApiService {
    @GET(ENDPOINT_WEATHER_STATE)
    fun getForecastData(@Query("lat") lan: Float?,
                        @Query("lon") lon: Float?,
                        @Query("units") units: String,
                        @Query("appid") api: String): Single<ForecastResponse>
}