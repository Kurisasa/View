package com.example.houndlocation.util.di;

import com.example.houndlocation.util.activities.AddEditLocationActivity;
import com.example.houndlocation.util.activities.DetailLocationActivity;
import com.example.houndlocation.util.activities.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract MainActivity bindMainListActivity();

    @ContributesAndroidInjector
    abstract AddEditLocationActivity bindAddEditLocationActivity();

    @ContributesAndroidInjector
    abstract DetailLocationActivity bindDetailsLocationActivity();

}
