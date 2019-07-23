package com.razan.weatherkotlin.di

import android.app.Application
import com.razan.weatherkotlin.context.KotlinWeatherApplication
import com.razan.weatherkotlin.di.vm.ViewModelFactoryProvider
import com.razan.weatherkotlin.di.vm.ViewModelsModule
import com.razan.weatherkotlin.adapters.BindingAdapters
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * This interface is marked with the [Component] annotation.
 * We should define all the modules that can be injected.
 * Also we provide AndroidSupportInjectionModule.class
 * here. This class was not created by me.
 * It is an internal class in Dagger 2.10.
 * Provides our activities and fragments with given module.
 */
@Singleton
@Component(
    modules = [
        ApiModule::class,
        ApplicationModuleProvider::class,
        ViewModelsModule::class,
        AndroidSupportInjectionModule::class,
        FragmentsBuilderModule::class,
        ActivitiesBuilderModule::class]
)
interface ApplicationComponent {

    /**
     * We will call this builder interface from our custom Application class.
     * This will set our application object to the ApplicationComponent.
     * So inside the [ApplicationComponent] the application instance is available.
     * So this application instance can be accessed by our modules
     * such as [ApiModule] when needed
     */
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    /**
     * This is our custom application class
     */
    fun inject(kotlinWeatherApplication: KotlinWeatherApplication)

    fun inject(instance: ViewModelFactoryProvider)

    fun inject(instance: BindingAdapters)
}