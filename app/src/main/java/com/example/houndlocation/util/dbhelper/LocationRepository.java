package com.example.houndlocation.util.dbhelper;

import android.os.AsyncTask;

import androidx.paging.DataSource;


import com.example.houndlocation.util.apirepo.Api;
import com.example.houndlocation.util.model.Location;
import com.example.houndlocation.util.model.WeatherData;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Single;

public class LocationRepository {

    private LocationDao locationDao;
    private Api api;
    private static final String WEATHER_API_KEY = "aa7aa59bfd60ddf73816ad836743ab40";

    @Inject
    public LocationRepository(LocationDao locationDao, Api api) {
        this.api = api;
        this.locationDao = locationDao;
    }

    public DataSource.Factory<Integer, Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

    public void insertLocation(Location location) {
        new insertAsyncTask(locationDao).execute(location);
    }

    public Single<Location> getLocationById(int locationID) {
        return locationDao.getLocationById(locationID);
    }

    public Single<Integer> updateLocation(Location location) {
        return locationDao.updateLocation(location);
    }

    public Single<WeatherData> getWeatherInformation(String lat, String lon) {
        return api.getLocationWeather(lat, lon, "metric", WEATHER_API_KEY);
    }

    public Location updateLocationWithWeather(WeatherData data, Location location) {

        location.setTemperature(data.getMain().getTemp() + " \u2103");
        location.setWeatherDate(new Date(TimeUnit.SECONDS.toMillis(data.getDt())));

        locationDao.updateLocation(location);

        return location;

    }

//
    private static class insertAsyncTask extends AsyncTask<Location, Void, Void> {
        private LocationDao mAsynclocationDao;

        public insertAsyncTask(LocationDao locationDao) {
            mAsynclocationDao = locationDao;
        }

        @Override
        protected Void doInBackground(final Location... locations) {
            mAsynclocationDao.insert(locations[0]);
            return null;
        }
    }
}
