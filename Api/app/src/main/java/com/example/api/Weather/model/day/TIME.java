package com.example.api.Weather.model.day;

import java.util.ArrayList;

public class TIME {
    String cod;
    String message;
    public ArrayList<Dailyh> list;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Dailyh> getDailies() {
        return list;
    }

    public void setDailies(ArrayList<Dailyh> dailies) {
        this.list = dailies;
    }

    public class Dailyh {
        Coordinates coord;
        public ArrayList<WeatherInfo> weather;
        public Main main;
        public Wind wind;
        public Clouds clouds;
        Sys sys;
        public int dt;
    }
    public class Sys {
        String country;
        String sunrise;
        String sunset;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }
    }

    public class Clouds {
        String all;

        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }
    }
    public class Wind {
        String speed;
        String deg;
        String gust;

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getDeg() {
            return deg;
        }

        public void setDeg(String deg) {
            this.deg = deg;
        }

        public String getGust() {
            return gust;
        }

        public void setGust(String gust) {
            this.gust = gust;
        }
    }

    public class Coordinates {
        String lon;
        String lat;

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }
    }
    public class Main {
        Double temp;
        String feels_like;
        Double temp_min;
        Double temp_max;
        String pressure;
        String humidity;
        String sea_level;
        String grnd_level;

        public Double getTemp() {
            return temp;
        }

        public void setTemp(Double temp) {
            this.temp = temp;
        }

        public String getFeels_like() {
            return feels_like;
        }

        public void setFeels_like(String feels_like) {
            this.feels_like = feels_like;
        }

        public Double getTemp_min() {
            return temp_min;
        }

        public void setTemp_min(Double temp_min) {
            this.temp_min = temp_min;
        }

        public Double getTemp_max() {
            return temp_max;
        }

        public void setTemp_max(Double temp_max) {
            this.temp_max = temp_max;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getSea_level() {
            return sea_level;
        }

        public void setSea_level(String sea_level) {
            this.sea_level = sea_level;
        }

        public String getGrnd_level() {
            return grnd_level;
        }

        public void setGrnd_level(String grnd_level) {
            this.grnd_level = grnd_level;
        }
    }

    public class WeatherInfo {
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

