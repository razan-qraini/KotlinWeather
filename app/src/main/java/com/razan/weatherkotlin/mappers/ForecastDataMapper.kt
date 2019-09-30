package com.razan.weatherkotlin.mappers

import com.razan.weatherkotlin.model.Forecast
import com.razan.weatherkotlin.model.ForecastResponse
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import com.razan.weatherkotlin.mappers.ForecastUIModel as ModelForecast

class ForecastDataMapper {

    fun convertFromDataModel(forecast: ForecastResponse):
            ForecastData =
        ForecastData(forecast.city.name, convertForecastListToDomain(forecast.list))

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {

        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(
            convertDate(forecast.dt),
            forecast.main.pressure,
            forecast.main.humidity,
            forecast.main.temp_max.toInt(),
            forecast.main.temp_min.toInt(),
            generateIconUrl(forecast.weather[0].icon)
        )
    }

    private fun generateIconUrl(iconCode: String): String =
        "http://openweathermap.org/img/w/$iconCode.png"

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date)
    }
}