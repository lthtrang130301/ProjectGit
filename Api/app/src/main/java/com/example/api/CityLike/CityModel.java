package com.example.api.CityLike;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "city")
public class CityModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String cityname;
    private String  description;

    public CityModel( String cityname, String description) {
        this.cityname = cityname;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
