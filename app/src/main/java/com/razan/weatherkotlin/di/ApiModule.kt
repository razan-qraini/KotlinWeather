package com.razan.weatherkotlin.di

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.razan.weatherkotlin.net.CountriesApiService
import com.razan.weatherkotlin.net.ForecastApiService
import com.razan.weatherkotlin.utils.COUNTRIES_BASE_URL
import com.razan.weatherkotlin.utils.ENDPOINT_WEATHER
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    /*
     * The method returns the Gson object
     * */
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }


    /*
     * The method returns the Cache object
     * */
    @Provides
    @Singleton
    fun provideCache(application: Application): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        val httpCacheDirectory = File(application.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }


    /*
     * The method returns the Okhttp object
     * */
    @Provides
    @Singleton
    fun provideOkhttpClient(cache: Cache): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.cache(cache)
        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        return httpClient.build()
    }


    /*
     * The method returns the Retrofit object
     * */
    @Provides
    @Singleton
    fun provideCountriesApiService(gson: Gson, okHttpClient: OkHttpClient): CountriesApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(COUNTRIES_BASE_URL)
            .client(okHttpClient)
            .build()
            .create(CountriesApiService::class.java)

    }

    /*
 * The method returns the Retrofit object
 * */
    @Provides
    @Singleton
    fun provideForecastApiService(gson: Gson, okHttpClient: OkHttpClient): ForecastApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(ENDPOINT_WEATHER)
            .client(okHttpClient)
            .build()
            .create(ForecastApiService::class.java)

    }
}