package com.example.houndlocation.util.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.houndlocation.util.dbhelper.LocationRepository;
import com.example.houndlocation.util.util.Utilities;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherViewModel extends ViewModel {

    private static final String TAG = WeatherViewModel.class.getName();

    public LocationRepository locationRepository;

    private MutableLiveData<String> detailLocationName = new MutableLiveData<String>();
    private MutableLiveData<String> detailLocationDescription = new MutableLiveData<String>();
    private MutableLiveData<String> detailLocationLon = new MutableLiveData<String>();
    private MutableLiveData<String> detailLocationLat = new MutableLiveData<String>();
    private MutableLiveData<String> detailTemperature = new MutableLiveData<String>();
    private MutableLiveData<String> detailTimeFetched = new MutableLiveData<String>();


    private int mLocationID;

    public WeatherViewModel() {
    }

    @Inject
    public WeatherViewModel(LocationRepository repository) {
        this.locationRepository = repository;

    }


    @SuppressLint("CheckResult")
    public void start(int locationID) {
        mLocationID = locationID;

        Log.d(TAG, "location id = " + locationID);

        if (locationID == 0) {

            throw new RuntimeException("locationID is null");
        }

        locationRepository
                .getLocationById(locationID)
                .flatMap(location -> locationRepository.getWeatherInformation(location.getLatitude(), location.getLongitude())
                        .map(w -> locationRepository.updateLocationWithWeather(w, location)))
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v -> {
                    detailLocationName.postValue(v.getName());
                    detailLocationDescription.postValue(v.getDescription());
                    detailTemperature.postValue(v.getTemperature());
                    detailLocationLat.postValue(v.getLatitude());
                    detailLocationLon.postValue(v.getLongitude());
                    detailTimeFetched.postValue(Utilities.dateToString(v.getWeatherDate()));
                }, e -> e.printStackTrace());

    }

    public MutableLiveData<String> getDetailLocationName() {
        return detailLocationName;
    }

    public MutableLiveData<String> getDetailLocationDescription() {
        return detailLocationDescription;
    }

    public MutableLiveData<String> getDetailLocationLon() {
        return detailLocationLon;
    }

    public MutableLiveData<String> getDetailLocationLat() {
        return detailLocationLat;
    }

    public MutableLiveData<String> getDetailTemperature() {
        return detailTemperature;
    }

    public MutableLiveData<String> getDetailTimeFetched() {
        return detailTimeFetched;
    }

}
