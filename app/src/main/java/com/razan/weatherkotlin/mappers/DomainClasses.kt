package com.razan.weatherkotlin.mappers

import java.io.Serializable

data class ForecastData(val countryName: String,
                        val dailyForecastList: List<ForecastUIModel>) {
    val size: Int
    get() = dailyForecastList.size

    operator fun get(position: Int) = dailyForecastList[position]
}

data class ForecastUIModel(val date: String, val pressure: Double, val humidity: Int,
                           val tempHigh: Int, val tempLow: Int, val iconUrl: String) : Serializable