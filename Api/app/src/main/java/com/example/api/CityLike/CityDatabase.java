package com.example.api.CityLike;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = CityModel.class, version = 1)
public abstract class CityDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "city.db";
    private static CityDatabase instance;
    public static synchronized CityDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), CityDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract CityDAO cityDAO();
}
