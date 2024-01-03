package com.example.api.Weather.api;


import com.example.api.Weather.model.Current;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API_CURRENT {
    @GET("weather")
    Call<Current> getweather(@Query("q") String cityname,
                             @Query("units") String temp,
                             @Query("appid") String apikey);

}
