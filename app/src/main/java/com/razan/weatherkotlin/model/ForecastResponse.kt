package com.razan.weatherkotlin.model

import com.google.gson.annotations.SerializedName

data class ForecastResponse (

    @SerializedName("cod") val cod : Int,
    @SerializedName("message") val message : Double,
    @SerializedName("cnt") val cnt : Int,
    @SerializedName("list") val list : List<Forecast>,
    @SerializedName("city") val city : City
)