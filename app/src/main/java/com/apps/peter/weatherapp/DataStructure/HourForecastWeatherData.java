package com.apps.peter.weatherapp.DataStructure;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "forecastweather_table")
public class HourForecastWeatherData {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "city_column")
    private String city;
    @ColumnInfo(name = "date_column")
    private long date;
    @ColumnInfo(name = "description_column")
    private String description;
    @ColumnInfo(name = "icon_column")
    private int icon;
    @ColumnInfo(name = "temperature_column")
    private int temperature;

    public HourForecastWeatherData(String city, long date, String description, int icon, int temperature) {
        this.city = city;
        this.date = date;
        this.description = description;
        this.icon = icon;
        this.temperature = temperature;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public long getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getIcon() {
        return icon;
    }

    public int getTemperature() {
        return temperature;
    }


}
