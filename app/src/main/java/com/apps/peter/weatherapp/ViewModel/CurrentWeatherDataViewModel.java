package com.apps.peter.weatherapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.apps.peter.weatherapp.DataStructure.CurrentWeatherData;
import com.apps.peter.weatherapp.Repository.WeatherRepository;

import java.util.List;

public class CurrentWeatherDataViewModel extends AndroidViewModel {
    private WeatherRepository repository;
    private LiveData<List<CurrentWeatherData>> allCurrentWeatherData;
    private LiveData<CurrentWeatherData> carioCurrentWeatherData;
    private LiveData<CurrentWeatherData> londonCurrentWeatherData;
    private LiveData<CurrentWeatherData> barcelonaCurrentWeatherData;
    private LiveData<CurrentWeatherData> parisCurrentWeatherData;
    private LiveData<CurrentWeatherData> munichCurrentWeatherData;


    public CurrentWeatherDataViewModel(@NonNull Application application) {
        super(application);
        repository = new WeatherRepository(application);
        carioCurrentWeatherData = repository.getCurrentWeatherDatabyCity("Cairo");
        londonCurrentWeatherData = repository.getCurrentWeatherDatabyCity("London");
        barcelonaCurrentWeatherData = repository.getCurrentWeatherDatabyCity("Barcelona");
        parisCurrentWeatherData = repository.getCurrentWeatherDatabyCity("Paris");
        munichCurrentWeatherData = repository.getCurrentWeatherDatabyCity("Munich");
    }

    public void insert(CurrentWeatherData currentWeatherData) {
        repository.insertCurrentWeatherData(currentWeatherData);
    }

    public void update(CurrentWeatherData currentWeatherData) {
        repository.updateCurrentWeatherData(currentWeatherData);
    }

    public void delete(CurrentWeatherData currentWeatherData) {
        repository.deleteCurrentWeatherData(currentWeatherData);
    }

    public void deleteAllCurrentWeatherData() {
        repository.deleteAllCurrentWeatherData();
    }

    public LiveData<CurrentWeatherData> getCairoCurrentWeatherData() {
        return carioCurrentWeatherData;
    }

    public LiveData<CurrentWeatherData> getLondonCurrentWeatherData() {
        return londonCurrentWeatherData;
    }

    public LiveData<CurrentWeatherData> getBarcelonaCurrentWeatherData() {
        return barcelonaCurrentWeatherData;
    }

    public LiveData<CurrentWeatherData> getParisCurrentWeatherData() {
        return parisCurrentWeatherData;
    }

    public LiveData<CurrentWeatherData> getMunichCurrentWeatherData() {
        return munichCurrentWeatherData;
    }
}
