package com.razan.weatherkotlin.ui.forecast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.razan.weatherkotlin.model.ForecastResponse
import com.razan.weatherkotlin.net.ForecastApiService
import com.razan.weatherkotlin.repos.ForecastRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ForecastViewModel @Inject constructor(
    forecastApiService: ForecastApiService) : ViewModel() {

    companion object {
        const val WEATHER_APPID = "1867722b6af87e1d0388e10c5a94be34"
        const val UNITS = "metric"
    }

    private val disposables = CompositeDisposable()
    private val forecastRepository: ForecastRepository = ForecastRepository(forecastApiService)

    // We are using LiveData to update the UI with the data changes.
    private val forecastLiveData = MutableLiveData<ForecastResponse>()

    // Fetch ForecastUIModel data
    fun getForecastData(lat: Float?, lon: Float?) {
        disposables.add(
            forecastRepository.getForecast(lat, lon, UNITS,
                WEATHER_APPID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { forecastResponse -> responseForecast().postValue(forecastResponse) },
                    { forecastLiveData.postValue(null) })
        )
    }

    // return mutable weather data model
    fun responseForecast() = forecastLiveData



    override fun onCleared() {
        super.onCleared()
        // clear the Composite Disposable
        disposables.clear()
    }
}
