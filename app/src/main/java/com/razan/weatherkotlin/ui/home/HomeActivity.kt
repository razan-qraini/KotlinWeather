package com.razan.weatherkotlin.ui.home

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.razan.weatherkotlin.R
import com.razan.weatherkotlin.databinding.ActivityHomeBinding
import com.razan.weatherkotlin.di.Injectable
import com.razan.weatherkotlin.model.Country
import com.razan.weatherkotlin.ui.countries.CountryClickCallback
import com.razan.weatherkotlin.ui.countries.CountriesListAdapter
import com.razan.weatherkotlin.ui.countries.CountriesViewModel
import com.razan.weatherkotlin.ui.countries.details.CountryDetailsFragment
import com.razan.weatherkotlin.ui.forecast.ForecastDetailsFragment
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.find
import timber.log.Timber
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HasSupportFragmentInjector, Injectable, CountryClickCallback {

    /*
     * The ViewModelFactory class has a list of ViewModels and will provide
     * the corresponding ViewModel in this activity
     * */
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    // I'm using Data Binding
    private lateinit var activityDrawerBinding: ActivityHomeBinding

    // This is our ViewModel class
    lateinit var countriesViewModel: CountriesViewModel
    private lateinit var countriesListAdapter: CountriesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        /*
         * We need to call this method in order to inject the
         * ViewModelFactory into our Activity, also that our fragment can
         * inject the ViewModelFactory
         * */
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        initialiseBinding()
        initialiseViewModel()
        initialiseDrawer()
    }

    private fun initialiseBinding() {
        // Binding
        activityDrawerBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        activityDrawerBinding.lifecycleOwner = this

        // RecyclerView
        countriesListAdapter = CountriesListAdapter(this)
        activityDrawerBinding.countriesList.layoutManager = LinearLayoutManager(this)
        activityDrawerBinding.countriesList.adapter = countriesListAdapter
    }

    /*
     * Initialising the ViewModel class here.
     * - Adding the ViewModelFactory class here.
     * - Observing the LiveData
     * */
    private fun initialiseViewModel() {
        countriesViewModel = ViewModelProviders.of(this, viewModelFactory).get(CountriesViewModel::class.java)

        countriesViewModel.responseCountriesLiveData().observe(this, Observer<List<Country>> { countries ->
            if (countries != null && countries.isNotEmpty()) {
                updateCountriesList(countries)

                addCountryFragment(countries[0])
                addForecastFragment(countries[0].latlng[0], countries[0].latlng[1])

            } else {
                Timber.d(HomeActivity::class.java.simpleName, "Empty list")
            }
        })
        // Fetch countries list
        countriesViewModel.getCountriesList()
    }

    private fun initialiseDrawer() {
        // Toolbar
        val toolbar: Toolbar = find(R.id.toolbar)
        toolbar.title = getString(R.string.toolbar_title)
        setSupportActionBar(toolbar)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.show()

        // DrawerLayout
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        //drawerLayout.openDrawer(Gravity.START)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun updateCountriesList(countries: List<Country>) {
        countriesListAdapter.setItems(countries)
    }

    private fun addCountryFragment(countryModel: Country) {

        val countryDetailsFragment =
            CountryDetailsFragment.newInstance(countryModel)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.countryDetailsContainer, countryDetailsFragment, "countryDetails")
            .addToBackStack(null)
            .commit()
    }

    override fun onCountryClicked(country: Country) {
        if (country != null) {
            addCountryFragment(country)
            addForecastFragment(country.latlng[0], country.latlng[1])
            drawerLayout.closeDrawers()
        }
    }

    private fun addForecastFragment(lat: Float, lon: Float) {

        val forecastFragment =
            ForecastDetailsFragment.newInstance(lat, lon)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.forecastContainer, forecastFragment, "forecastDetails")
            .addToBackStack(null)
            .commit()
    }

    /*
     * Rather than injecting the ViewModelFactory
     * in the activity, we are going to implement the
     * HasActivityInjector and inject the ViewModelFactory
     * into our Fragments
     * */
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}
