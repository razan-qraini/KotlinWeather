package com.razan.weatherkotlin.di.vm

import android.content.Context
import com.razan.weatherkotlin.di.ApplicationDependencyInjector
import javax.inject.Inject

class ViewModelFactoryProvider(context: Context) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    init {
        ApplicationDependencyInjector.inject(context, this)
    }
}