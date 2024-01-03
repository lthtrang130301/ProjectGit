package com.example.api.Weather.model.day;

import com.example.api.Weather.model.Clouds;
import com.example.api.Weather.model.Main;
import com.example.api.Weather.model.Sys;
import com.example.api.Weather.model.Wind;

import java.util.ArrayList;

public class Dailyh {
    Integer dt;
    Main main;
    Wind wind;
    Clouds clouds;
    Sys sys;
    String dt_txt;
    ArrayList<WeatherInfo> weather;

    public Integer getDt() {
        return dt;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Sys getSys() {
        return sys;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public ArrayList<WeatherInfo> getWeather() {
        return weather;
    }

    public class WeatherInfo{
        String id;
        String main;
        String description;
        String icon;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
