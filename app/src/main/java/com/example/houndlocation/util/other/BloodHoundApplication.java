package com.example.houndlocation.util.other;

import android.app.Activity;
import android.app.Application;

import com.example.houndlocation.util.di.AppComponent;
import com.example.houndlocation.util.di.AppModule;
import com.example.houndlocation.util.di.DataModule;
import com.example.houndlocation.util.di.DaggerAppComponent;
import javax.inject.Inject;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class BloodHoundApplication extends Application implements HasActivityInjector {

    private static BloodHoundApplication app;
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        initDi();

        appComponent.inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    public static BloodHoundApplication getApp(){
        return app;
    }
    private void initDi() {

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .appModule(new AppModule(this))
                .dataModule(new DataModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
