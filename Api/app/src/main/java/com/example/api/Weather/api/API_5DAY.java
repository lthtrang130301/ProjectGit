package com.example.api.Weather.api;

import com.example.api.Weather.model.day.TIME;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API_5DAY {
    @GET("forecast")
    Call<TIME> getweather2(@Query("q") String cityname,
                           @Query("units") String temp,
                           @Query("appid") String apikey);
}
