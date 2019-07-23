package com.razan.weatherkotlin.ui.forecast.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.razan.weatherkotlin.R
import com.razan.weatherkotlin.databinding.FragmentDailyForecastBinding
import com.razan.weatherkotlin.mappers.ForecastUIModel
import com.squareup.picasso.Picasso


class DailyForecastFragment : Fragment() {

    private lateinit var dailyForecastFragmentBinding: FragmentDailyForecastBinding
    private lateinit var forecast: ForecastUIModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            forecast = it.getSerializable(ARG_FORECAST) as ForecastUIModel
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dailyForecastFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_daily_forecast, container, false)
        dailyForecastFragmentBinding.forecastItem = forecast
        Picasso.get().load(forecast.iconUrl).fit().into(dailyForecastFragmentBinding.weatherIconIv)
        return dailyForecastFragmentBinding.root
    }

    companion object {
        private const val ARG_FORECAST = "forecast"

        @JvmStatic
        fun newInstance(forecastUIModel: ForecastUIModel) =
            DailyForecastFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_FORECAST, forecastUIModel)
                }
            }
    }
}
