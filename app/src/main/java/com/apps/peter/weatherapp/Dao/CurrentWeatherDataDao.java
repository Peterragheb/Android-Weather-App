package com.apps.peter.weatherapp.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.apps.peter.weatherapp.DataStructure.CurrentWeatherData;
@Dao
public interface CurrentWeatherDataDao {
    @Insert
    void insert(CurrentWeatherData currentWeatherData);

    @Update
    void update(CurrentWeatherData currentWeatherData);

    @Delete
    void delete(CurrentWeatherData currentWeatherData);

    @Query("DELETE FROM currentweather_table")
    void deleteAllCurrentWeatherData();

    @Query("SELECT * FROM currentweather_table WHERE cityName_column = :cityName")
    LiveData<CurrentWeatherData> getLiveCurrentWeatherDataByCity(String cityName);

    @Query("SELECT * FROM currentweather_table WHERE cityName_column = :cityName")
    CurrentWeatherData getCurrentWeatherDataByCity(String cityName);

    @Query("UPDATE currentweather_table SET temperature_column = :temperature WHERE cityName_column = :cityName")
    void UpdateCurrentWeatherDataByCity(String cityName,int temperature);

}
