package com.razan.weatherkotlin.di.module

import com.razan.weatherkotlin.net.CountriesApiService
import com.razan.weatherkotlin.utils.COUNTRIES_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object CountriesModule {

    /**
     * Provides the Countries service implementation
     * @param retrofit the Retrofit  object used to instantiate the service
     * @return the Countries service implementation
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideCountriesApi(retrofit: Retrofit): CountriesApiService {
        return retrofit.create(CountriesApiService::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(COUNTRIES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}