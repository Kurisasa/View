package com.example.houndlocation.util.model;

import com.squareup.moshi.Json;

public class Rain {

    @Json(name = "3h")
    private Integer _3h;

    public Integer get3h() {
        return _3h;
    }

    public void set3h(Integer _3h) {
        this._3h = _3h;
    }
}
