package com.razan.weatherkotlin.ui.countries

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.razan.weatherkotlin.model.Country
import com.razan.weatherkotlin.net.CountriesApiService
import com.razan.weatherkotlin.repos.CountryRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CountriesViewModel @Inject constructor(
    countriesApiService: CountriesApiService
) : ViewModel() {

    private val disposables = CompositeDisposable()
    private val countryListRepository: CountryRepository = CountryRepository(countriesApiService)

    // We are using LiveData to update the UI with the data changes.
    private val countriesListLiveData = MutableLiveData<List<Country>>()

    /**
     * Method called by UI to fetch countries list
     * */
    fun getCountriesList() {
        disposables.add(
            countryListRepository.fetchCountriesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { countryList -> responseCountriesLiveData().postValue(countryList) },
                    { countriesListLiveData.postValue(null) })
        )
    }

    /**
     * LiveData observed by the UI
     */
    fun responseCountriesLiveData() = countriesListLiveData

    override fun onCleared() {
        // clear the composite disposable
        disposables.clear()
    }
}