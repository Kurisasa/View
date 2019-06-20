package com.example.houndlocation.util.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.houndlocation.util.viewmodel.AddLocationViewModel;
import com.example.houndlocation.util.viewmodel.DaggerViewModelFactory;
import com.example.houndlocation.util.viewmodel.WeatherViewModel;
import com.example.houndlocation.util.viewmodel.LocationListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(DaggerViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(LocationListViewModel.class)
    abstract ViewModel provideLocationListViewModel(LocationListViewModel locationListViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(AddLocationViewModel.class)
    abstract ViewModel provideAddEditLocationViewModel(AddLocationViewModel addEditLocationViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel.class)
    abstract ViewModel provideDetailLocationViewModel(WeatherViewModel weatherViewModel);

}
