package com.example.api.Weather.api;

import com.example.api.Weather.model.day.TIME;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API_TIME {
    @GET("forecast")
    Call<TIME> getweather1(@Query("q") String cityname,
                           @Query("units") String temp,
                           @Query("cnt") String days,
                           @Query("appid") String apikey);
}
