package com.example.api.Weather.model;

import java.util.ArrayList;

public class Current {

    Coordinates coord;
    ArrayList<WeatherInfo> weather;
    Main main;
    Wind wind;
    Clouds clouds;
    Sys sys;
    Integer dt;
    public Integer getDt() {
        return dt;
    }

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public ArrayList<WeatherInfo> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<WeatherInfo> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }


    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }


}

