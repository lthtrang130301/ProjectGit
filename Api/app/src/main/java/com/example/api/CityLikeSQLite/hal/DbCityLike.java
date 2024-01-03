package com.example.api.CityLikeSQLite.hal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class DbCityLike {
    DbHelper dbHelper;

    public DbCityLike(Context context) {
        dbHelper = new DbHelper(context);
    }

//    public ArrayList<CityModel> checkcity(String TenCity){
//
////        ArrayList<CityModel> data = new ArrayList<>();
////        String sql = "select * from City Where TenCity = :TenCity ";
////        SQLiteDatabase db = dbHelper.getReadableDatabase();
////        Cursor cursor = db.rawQuery(sql, null);
////        try {
////            cursor.moveToFirst();
////            do {
////                CityModel cityModel = new CityModel();
////                cityModel.setId(Integer.valueOf(cursor.getString(0)));
////                cityModel.setTenCity(cursor.getString(1));
////                cityModel.setMota(cursor.getString(2));
////                data.add(cityModel);
////            } while (cursor.moveToNext());
////        } catch (Exception ex) {
////
////        }
////        return data;
//    }
    public void Them(CityModel cityModel) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tencity", cityModel.getTenCity());
        values.put("mota", cityModel.getMota());
        db.insert("City", null, values);
    }

    public int DELETE(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete("City", "id=?", new String[]{String.valueOf(id)});

    }
    public void XoaHet(CityModel cityModel) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "DELETE FROM City ";
        db.execSQL(sql);

    }
    public ArrayList<CityModel> Timkiem(String TenCity){

        ArrayList<CityModel> data = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "SELECT * FROM City WHERE tencity LIKE '%' || :TenCity || '%' ";
        Cursor cursor = db.rawQuery(sql, null);
        //return data;
        return (ArrayList<CityModel>) cursor;
    }

//    public void Sua(CityModel cityLike) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("Tencity", cityLike.getTenCity());
//        values.put("Mota", cityLike.getMota());
//        db.update("CityModel", values, "Tencity = '" + cityLike.getTenCity() + "'", null);
//    }
    public ArrayList<CityModel> LayDL() {
        ArrayList<CityModel> data = new ArrayList<>();
        String sql = "select * from City";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                CityModel cityModel = new CityModel();
                cityModel.setId(Integer.valueOf(cursor.getString(0)));
                cityModel.setTenCity(cursor.getString(1));
                cityModel.setMota(cursor.getString(2));
                data.add(cityModel);
            } while (cursor.moveToNext());
        } catch (Exception ex) {

        }
        return data;
    }

}
