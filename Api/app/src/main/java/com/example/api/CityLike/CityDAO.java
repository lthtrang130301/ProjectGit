package com.example.api.CityLike;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CityDAO {
    @Insert
    void insertCity(CityModel cityModel);

    @Query("SELECT * FROM city")
    List<CityModel> getListCity();
    @Query("SELECT * FROM city Where cityname = :cityname")
    List<CityModel> checkcity(String cityname);

    @Delete
    void deleteButton(CityModel cityModel);
    @Query("DELETE FROM city")
    void ClearAll();

    @Query("SELECT * FROM city WHERE cityname LIKE '%' || :name ||'%' ")
    List<CityModel> searchCity(String name);

}
