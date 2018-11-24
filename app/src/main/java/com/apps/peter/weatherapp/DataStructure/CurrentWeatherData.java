package com.apps.peter.weatherapp.DataStructure;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "currentweather_table")
public class CurrentWeatherData {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "cityName_column")
    private String cityName;
    @ColumnInfo(name = "date_column")
    private String date;
    @ColumnInfo(name = "temperature_column")
    private int temperature;

    public CurrentWeatherData(String cityName, String date, int temperature) {
        this.cityName = cityName;
        this.date = date;
        this.temperature = temperature;
    }


    public String getCityName() {
        return cityName;
    }

    public String getDate() {
        return date;
    }

    public int getTemperature() {
        return temperature;
    }
}
