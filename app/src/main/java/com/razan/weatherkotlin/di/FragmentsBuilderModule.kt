package com.razan.weatherkotlin.di

import com.razan.weatherkotlin.ui.countries.details.CountryDetailsFragment
import com.razan.weatherkotlin.ui.forecast.ForecastDetailsFragment
import com.razan.weatherkotlin.ui.forecast.daily.DailyForecastFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindCountryDetailsFragment(): CountryDetailsFragment

    @ContributesAndroidInjector
    abstract fun bindForecastDetailsFragment(): ForecastDetailsFragment

    @ContributesAndroidInjector
    abstract fun bindDailyForecastFragment(): DailyForecastFragment
}
