package com.razan.weatherkotlin.repos

import com.razan.weatherkotlin.model.ForecastResponse
import com.razan.weatherkotlin.net.ForecastApiService
import io.reactivex.Single

class ForecastRepository(private val forecastApiService: ForecastApiService) {

    fun getForecast(lan: Float?, lon: Float?, units: String, appId: String): Single<ForecastResponse> {
        return forecastApiService.getForecastData(lan, lon, units, appId)
    }
}