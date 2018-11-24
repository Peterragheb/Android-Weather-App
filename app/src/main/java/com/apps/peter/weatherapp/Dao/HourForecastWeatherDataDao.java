package com.apps.peter.weatherapp.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.apps.peter.weatherapp.DataStructure.HourForecastWeatherData;

import java.util.List;
@Dao
public interface HourForecastWeatherDataDao {
    @Insert
    void insert(HourForecastWeatherData hourForecastWeatherData);

    @Update
    void update(HourForecastWeatherData hourForecastWeatherData);

    @Delete
    void delete(HourForecastWeatherData hourForecastWeatherData);

    @Query("DELETE FROM forecastweather_table")
    void deleteAllHourForecastWeatherData();

    @Query("SELECT * FROM forecastweather_table ORDER BY id ASC")
    LiveData<List<HourForecastWeatherData>> getAllHourForecastWeatherData();

    @Query("SELECT * FROM forecastweather_table WHERE city_column = :cityName")
    LiveData<List<HourForecastWeatherData>> getLiveHourWeatherDataByCity(String cityName);

    @Query("SELECT * FROM forecastweather_table WHERE city_column = :cityName")
    List<HourForecastWeatherData> getHourWeatherDataByCity(String cityName);

    @Query("UPDATE currentweather_table SET temperature_column = :temperature WHERE cityName_column = :cityName and date_column = :date")
    void UpdateHourWeatherDataByCity(String cityName,String date,int temperature);

    @Query("DELETE FROM forecastweather_table WHERE city_column = :cityName")
    void deleteAllHourForecastWeatherDataByCity(String cityName);
}