package com.razan.weatherkotlin.net

import com.razan.weatherkotlin.model.Country
import com.razan.weatherkotlin.utils.ENDPOINT_COUNTRY
import io.reactivex.Single
import retrofit2.http.GET

interface CountriesApiService {
    @GET(ENDPOINT_COUNTRY)
    fun getCountries(): Single<List<Country>>
}