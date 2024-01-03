package com.example.api.CityLikeSQLite.hal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "QLCity", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table City(id INTEGER PRIMARY KEY ,tencity text, mota text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "City");
        onCreate(db);

    }
}
