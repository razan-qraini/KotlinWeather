package com.razan.weatherkotlin.ui.countries

import com.razan.weatherkotlin.model.Country

interface CountryClickCallback {
    fun onCountryClicked(country: Country)
}
