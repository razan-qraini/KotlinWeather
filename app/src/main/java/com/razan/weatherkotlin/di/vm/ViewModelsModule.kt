package com.razan.weatherkotlin.di.vm;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.razan.weatherkotlin.ui.countries.CountriesViewModel
import com.razan.weatherkotlin.ui.forecast.ForecastViewModel
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelsModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    /**
     * This method basically says
     * Inject this object into a map using the [IntoMap] annotation,
     * with the [CountriesViewModel] as key, and a Provider that
     * will build a [CountriesViewModel] object
     */
    @Binds
    @IntoMap
    @ViewModelKey(CountriesViewModel::class)
    protected abstract fun bindCountriesViewModel(countriesViewModel: CountriesViewModel): ViewModel

    /**
     * Inject this object into a map using the [IntoMap] annotation,
     * with the [ForecastViewModel] as key, and a Provider that
     * will build a [ForecastViewModel] object
     */
    @Binds
    @IntoMap
    @ViewModelKey(ForecastViewModel::class)
    protected abstract fun bindForecastViewModel(forecastViewModel: ForecastViewModel): ViewModel
}
