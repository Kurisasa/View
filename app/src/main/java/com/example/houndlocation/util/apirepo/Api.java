package com.example.houndlocation.util.apirepo;

import com.example.houndlocation.util.model.WeatherData;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("weather")
    Single<WeatherData> getLocationWeather(@Query("lat") String latitude,
                                           @Query("lon") String longitude,
                                           @Query("units") String units,
                                           @Query("appid") String appid);
}
