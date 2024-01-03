package com.example.api.CityLikeSQLite.hal;

public class CityModel {
    Integer id;
    String TenCity, Mota;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenCity() {
        return TenCity;
    }

    public void setTenCity(String TenCity) {
        this.TenCity = TenCity;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String Mota) {
        this.Mota = Mota;
    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id +'\''+
                ", TenCity='" + TenCity + '\'' +
                ", Mota='" + Mota + '\'' +
                '}';
    }
}
