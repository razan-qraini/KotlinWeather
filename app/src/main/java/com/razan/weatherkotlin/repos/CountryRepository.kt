package com.razan.weatherkotlin.repos

import com.razan.weatherkotlin.model.Country
import com.razan.weatherkotlin.net.CountriesApiService
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Singleton

@Singleton
class CountryRepository(private val countriesApiService: CountriesApiService) : Repository {

    init {
        Timber.d("Injection CountryRepository")
    }

    fun fetchCountriesList(): Single<List<Country>> {
        return countriesApiService.getCountries()
    }
}