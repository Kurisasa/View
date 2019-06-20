package com.example.houndlocation.util.model;

import com.squareup.moshi.Json;

import java.util.Date;

public class Sys {

    @Json(name = "type")
    private Integer type;
    @Json(name = "id")
    private Integer id;
    @Json(name = "message")
    private Double message;
    @Json(name = "country")
    private String country;
    @Json(name = "sunrise")
    private Integer sunrise;
    @Json(name = "sunset")
    private Integer sunset;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public Integer getSunset() {
        return sunset;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }
}
