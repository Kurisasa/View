package com.example.houndlocation.util.model;

import com.squareup.moshi.Json;

public class Coordinate {

    @Json(name = "lon")
    private Double lon;
    @Json(name = "lat")
    private Double lat;

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}
