package com.razan.weatherkotlin.ui.forecast

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.razan.weatherkotlin.mappers.ForecastUIModel
import com.razan.weatherkotlin.ui.forecast.daily.DailyForecastFragment

class ForecastPagerAdapter(fragmentManager: FragmentManager?, private val forecastItems: List<ForecastUIModel>):
    FragmentStatePagerAdapter(fragmentManager!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getItem(position: Int): Fragment {
        return DailyForecastFragment.newInstance(forecastItems[position])
    }

    override fun getCount(): Int = if (forecastItems.size > 1) 2 else 1

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Today"
            1 -> "Tomorrow"
            else -> ""
        }
    }
}
