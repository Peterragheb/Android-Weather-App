package com.apps.peter.weatherapp.Dao;

import com.apps.peter.weatherapp.DataStructure.CurrentForecastApi;
import com.apps.peter.weatherapp.DataStructure.WeekWeatherForecastApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClient {
    @GET("weather?units=metric&type=like&mode=json&appid=140e9e7252937cbaf65160f41d89b5ca")
    Call<CurrentForecastApi> getCurrentForecast(@Query("q")String city);

    @GET("forecast?units=metric&type=like&mode=json&appid=140e9e7252937cbaf65160f41d89b5ca")
    Call<WeekWeatherForecastApi> getWeekForecast(@Query("q")String city);
}
