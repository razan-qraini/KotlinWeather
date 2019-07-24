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

    companion object {
        const val ARG_COUNTRY_MODEL = "country_model"

        fun newInstance(selectedCountry: Country) = CountryDetailsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ARG_COUNTRY_MODEL, selectedCountry)
            }
        }
    }

    private lateinit var selectedCountry: Country
    private lateinit var binding: FragmentCountryDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            selectedCountry = getSerializable(ARG_COUNTRY_MODEL) as Country
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_country_details, container, false)
        with(binding) {
            country = selectedCountry
            countryCode = selectedCountry.alpha2Code
        }
        return binding.root
    }
}
