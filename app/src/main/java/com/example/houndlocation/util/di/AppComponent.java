package com.example.houndlocation.util.di;

import com.example.houndlocation.util.other.LocationApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules={AndroidInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class,
        DataModule.class,
        ViewModelModule.class})

public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance Builder application(LocationApplication application);
        Builder appModule(AppModule appModule);
        Builder dataModule(DataModule dataModule);
        AppComponent build();

    }

    void inject(LocationApplication locationApplication);

//    void inject(LocationRepository repository);

//    void inject(LocationListViewModel locationListViewModel);
//
//    void inject(AddEditLocationViewModel addEditLocationViewModel);
//
//    void inject(DetailLocationViewModel detailLocationViewModel);
}
