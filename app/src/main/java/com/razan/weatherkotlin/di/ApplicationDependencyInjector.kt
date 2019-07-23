package com.razan.weatherkotlin.di

import android.content.Context
import com.razan.weatherkotlin.context.KotlinWeatherApplication
import com.razan.weatherkotlin.di.vm.ViewModelFactoryProvider
import com.razan.weatherkotlin.adapters.BindingAdapters

object ApplicationDependencyInjector {

    private fun getApplicationComponent(context: Context): ApplicationComponent =
        (context.applicationContext as KotlinWeatherApplication).applicationComponent

    fun inject(context: Context, instance: ViewModelFactoryProvider) =
        getApplicationComponent(context).inject(instance)

    fun inject(context: Context, instance: BindingAdapters) =
        getApplicationComponent(context).inject(instance)
}