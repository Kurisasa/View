package com.example.houndlocation.util.di;

import com.example.houndlocation.util.activities.AddLocationActivity;
import com.example.houndlocation.util.activities.WeatherActivity;
import com.example.houndlocation.util.activities.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract MainActivity bindMainListActivity();

    @ContributesAndroidInjector
    abstract AddLocationActivity bindAddEditLocationActivity();

    @ContributesAndroidInjector
    abstract WeatherActivity bindDetailsLocationActivity();

}
