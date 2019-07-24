package com.razan.weatherkotlin.model

import com.google.gson.annotations.SerializedName

data class Forecast(

    @SerializedName("dt") val dt: Long,
    @SerializedName("main") val main: Main,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("sys") val sys: Sys,
    @SerializedName("dt_txt") val dateTxt: String
)