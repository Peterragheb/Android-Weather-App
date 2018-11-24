package com.apps.peter.weatherapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.apps.peter.weatherapp.DataStructure.HourForecastWeatherData;
import com.apps.peter.weatherapp.Repository.WeatherRepository;

import java.util.List;

public class HourForecastWeatherDataViewModel extends AndroidViewModel {
    private WeatherRepository repository;
    private LiveData<List<HourForecastWeatherData>> allHourForecastWeatherData;
    private MutableLiveData<String> cityName = new MutableLiveData<>();

    public HourForecastWeatherDataViewModel(@NonNull Application application) {
        super(application);
        repository = new WeatherRepository(application);
        allHourForecastWeatherData = Transformations.switchMap(cityName, name -> {
            return repository.getWeekWeatherDatabyCity(name);
        });
    }

    public void setcityName(String cityName) {
        this.cityName.setValue(cityName); // This will trigger the switchMap statements
    }

    public void insert(HourForecastWeatherData hourForecastWeatherData) {
        repository.insertHourForecastWeatherData(hourForecastWeatherData);
    }

    public void update(HourForecastWeatherData hourForecastWeatherData) {
        repository.updateHourForecastWeatherData(hourForecastWeatherData);
    }

    public void delete(HourForecastWeatherData hourForecastWeatherData) {
        repository.deleteHourForecastWeatherData(hourForecastWeatherData);
    }

    public void deleteAllHourWeatherData() {
        repository.deleteAllHourForecastWeatherData();
    }

    public LiveData<List<HourForecastWeatherData>> getAllHourWeatherData() {
        return allHourForecastWeatherData;
    }

}
