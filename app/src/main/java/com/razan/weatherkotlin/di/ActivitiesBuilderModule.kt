package com.razan.weatherkotlin.di;

import com.razan.weatherkotlin.ui.home.HomeActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@SuppressWarnings("unused")
@Module
abstract class ActivitiesBuilderModule {

    @ContributesAndroidInjector
    abstract fun homeActivity(): HomeActivity
}