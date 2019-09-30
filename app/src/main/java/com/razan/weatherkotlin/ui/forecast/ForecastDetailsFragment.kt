package com.razan.weatherkotlin.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.razan.weatherkotlin.R
import com.razan.weatherkotlin.databinding.FragmentForecastDetailsBinding
import com.razan.weatherkotlin.di.Injectable
import com.razan.weatherkotlin.mappers.ForecastDataMapper
import com.razan.weatherkotlin.mappers.ForecastData
import com.razan.weatherkotlin.model.Country
import com.razan.weatherkotlin.model.ForecastResponse
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_forecast_details.*
import timber.log.Timber
import javax.inject.Inject

class ForecastDetailsFragment : Fragment(), Injectable {

    private var latitude: Float? = null
    private var longitude: Float? = null

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ForecastViewModel
    private lateinit var forecastDetailsFragmentBinding: FragmentForecastDetailsBinding
    private lateinit var pagerAdapter: ForecastPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
         * We need to call this method in order to inject the
         * ViewModelFactory into this Fragment
         * */
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        forecastDetailsFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_forecast_details, container, false)
        return forecastDetailsFragmentBinding.root
    }

    private fun observeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ForecastViewModel::class.java)

        viewModel.responseForecast().observe(this, Observer<ForecastResponse> { forecasts ->
            if (forecasts != null) {
                // Convert response data and pass it to viewPager
                initViewPager(ForecastDataMapper().convertFromDataModel(forecasts))
            } else {
                Timber.d(ForecastDetailsFragment::class.java.simpleName, "Empty Forecast list")
            }
        })
        viewModel.getForecastData(latitude, longitude)
    }

    private fun initViewPager(forecastData: ForecastData) {
        pagerAdapter = ForecastPagerAdapter(childFragmentManager, forecastData.dailyForecastList)
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    fun updateForecastView(country: Country) {
        latitude = country.latlng[0]
        longitude = country.latlng[1]

        observeViewModel()
    }
}
