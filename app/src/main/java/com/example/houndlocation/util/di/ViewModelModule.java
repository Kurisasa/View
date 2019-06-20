package com.example.houndlocation.util.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.houndlocation.util.viewmodel.AddEditLocationViewModel;
import com.example.houndlocation.util.viewmodel.DaggerViewModelFactory;
import com.example.houndlocation.util.viewmodel.DetailLocationViewModel;
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
    @ViewModelKey(AddEditLocationViewModel.class)
    abstract ViewModel provideAddEditLocationViewModel(AddEditLocationViewModel addEditLocationViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(DetailLocationViewModel.class)
    abstract ViewModel provideDetailLocationViewModel(DetailLocationViewModel detailLocationViewModel);

}
