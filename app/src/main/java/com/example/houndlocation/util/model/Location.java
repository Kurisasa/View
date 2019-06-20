package com.example.houndlocation.util.model;

import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "location")
public class Location {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "location_state")
    private Boolean locationState;

    @ColumnInfo(name = "location_type_name")
    private String locationTypeName;

    @Nullable
    @ColumnInfo(name = "minor")
    private String minor;

    @Nullable
    @ColumnInfo(name = "major")
    private String major;


    @ColumnInfo(name = "longitude")
    private String longitude;


    @ColumnInfo(name = "latitude")
    private String latitude;

    @Nullable
    @ColumnInfo(name = "weather_date")
    private Date weatherDate;

    @Nullable
    @ColumnInfo(name = "temperature")
    private String temperature;


    public Location(@Nullable String name, String description, Boolean locationState, String locationTypeName,  @Nullable String minor,@Nullable String major, @Nullable String longitude,@Nullable String latitude,@Nullable Date weatherDate,@Nullable String temperature) {
        this.name = name;
        this.description = description;
        this.locationState = locationState;
        this.locationTypeName = locationTypeName;
        this.minor = minor;
        this.major = major;
        this.longitude = longitude;
        this.latitude = latitude;
        this.weatherDate = weatherDate;
        this.temperature = temperature;
    }

    @Ignore
    public Location(String name, String description, Boolean locationState, String locationTypeName,  String longitude, String latitude) {
        this.name = name;
        this.description = description;
        this.locationState = locationState;
        this.locationTypeName = locationTypeName;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Ignore
    public Location(int uid, String name, String description, Boolean locationState, String locationTypeName, String longitude, String latitude) {
        this.uid = uid;
        this.name = name;
        this.description = description;
        this.locationState = locationState;
        this.locationTypeName = locationTypeName;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Date getWeatherDate() {
        return weatherDate;
    }

    public void setWeatherDate(Date weatherDate) {
        this.weatherDate = weatherDate;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public Boolean getLocationState() {
        return locationState;
    }

    public void setLocationState(Boolean locationState) {
        this.locationState = locationState;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocationTypeName() {
        return locationTypeName;
    }

    public void setLocationTypeName(String locationTypeName) {
        this.locationTypeName = locationTypeName;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public boolean isEmpty(){

        return TextUtils.isEmpty(name) && TextUtils.isEmpty(longitude) && TextUtils.isEmpty(latitude);

    }
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this)
            return true;

        Location location = (Location) obj;
        if (location != null) {
            return location.uid == this.uid;
        }
        return false;
    }
}
