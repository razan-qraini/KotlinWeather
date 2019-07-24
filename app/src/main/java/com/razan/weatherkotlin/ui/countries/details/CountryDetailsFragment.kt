package com.razan.weatherkotlin.ui.countries.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.razan.weatherkotlin.R
import com.razan.weatherkotlin.databinding.FragmentCountryDetailsBinding
import com.razan.weatherkotlin.model.Country

class CountryDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCountryDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_country_details, container, false)
        return binding.root
    }

    fun updateCountryView(country: Country) {
        binding.country = country
        binding.countryCode = country.alpha2Code
    }
}
