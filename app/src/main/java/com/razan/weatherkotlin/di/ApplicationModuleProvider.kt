package com.razan.weatherkotlin.di

import android.content.Context
import com.razan.weatherkotlin.context.KotlinWeatherApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides dependency objects like Retrofit, Utils, Context, Gson, etc
 */
@Module
class ApplicationModuleProvider {

    @Provides
    @Singleton
    fun provideApplicationContext(kotlinWeatherApplication: KotlinWeatherApplication):
            Context = kotlinWeatherApplication
}