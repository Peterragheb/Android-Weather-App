package com.apps.peter.weatherapp.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.apps.peter.weatherapp.Dao.ApiClient;
import com.apps.peter.weatherapp.Dao.CurrentWeatherDataDao;
import com.apps.peter.weatherapp.Dao.HourForecastWeatherDataDao;
import com.apps.peter.weatherapp.DataStructure.CurrentForecastApi;
import com.apps.peter.weatherapp.DataStructure.CurrentWeatherData;
import com.apps.peter.weatherapp.DataStructure.HourForecastWeatherData;
import com.apps.peter.weatherapp.DataStructure.WeekWeatherForecastApi;
import com.apps.peter.weatherapp.Database.WeatherDatabase;
import com.apps.peter.weatherapp.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepository {
    private CurrentWeatherDataDao currentWeatherDataDao;
    private HourForecastWeatherDataDao hourForecastWeatherDataDao;
    private LiveData<List<HourForecastWeatherData>> allHourForecastWeatherData;
    private Retrofit retrofit;
    private ApiClient apiClient;
    private List<CurrentWeatherData> ApiList;

    public WeatherRepository(Application application) {
        WeatherDatabase database = WeatherDatabase.getInstance(application);
        currentWeatherDataDao = database.CurrentWeatherDataDao();
        hourForecastWeatherDataDao = database.HourForecastWeatherDataDao();
        allHourForecastWeatherData = hourForecastWeatherDataDao.getAllHourForecastWeatherData();
        ApiList = Collections.synchronizedList(new ArrayList<CurrentWeatherData>());
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiClient = retrofit.create(ApiClient.class);
    }

    public void insertCurrentWeatherData(CurrentWeatherData currentWeatherData) {
        new InsertCurrentWeatherDataAsyncTask(currentWeatherDataDao).execute(currentWeatherData);
    }

    public void updateCurrentWeatherData(CurrentWeatherData currentWeatherData) {
        new UpdateCurrentWeatherDataAsyncTask(currentWeatherDataDao).execute(currentWeatherData);

    }

    public void deleteCurrentWeatherData(CurrentWeatherData currentWeatherData) {
        new DeleteCurrentWeatherDataAsyncTask(currentWeatherDataDao).execute(currentWeatherData);

    }

    public void deleteAllCurrentWeatherData() {
        new DeleteAllCurrentWeatherDataAsyncTask(currentWeatherDataDao).execute();

    }

    public void insertHourForecastWeatherData(HourForecastWeatherData hourForecastWeatherData) {
        new InsertHourForecastWeatherDataAsyncTask(hourForecastWeatherDataDao).execute(hourForecastWeatherData);

    }

    public void updateHourForecastWeatherData(HourForecastWeatherData hourForecastWeatherData) {
        new UpdateHourForecastWeatherDataAsyncTask(hourForecastWeatherDataDao).execute(hourForecastWeatherData);

    }

    public void deleteHourForecastWeatherData(HourForecastWeatherData hourForecastWeatherData) {
        new DeleteHourForecastWeatherDataAsyncTask(hourForecastWeatherDataDao).execute(hourForecastWeatherData);

    }

    public void deleteAllHourForecastWeatherData() {
        new DeleteAllHourForecastWeatherDataAsyncTask(hourForecastWeatherDataDao).execute();
    }

    private static class InsertCurrentWeatherDataAsyncTask extends AsyncTask<CurrentWeatherData, Void, Void> {
        private CurrentWeatherDataDao currentWeatherDataDao;

        private InsertCurrentWeatherDataAsyncTask(CurrentWeatherDataDao currentWeatherDataDao) {
            this.currentWeatherDataDao = currentWeatherDataDao;
        }

        @Override
        protected Void doInBackground(CurrentWeatherData... currentWeatherData) {
            currentWeatherDataDao.insert(currentWeatherData[0]);
            return null;
        }
    }

    private static class UpdateCurrentWeatherDataAsyncTask extends AsyncTask<CurrentWeatherData, Void, Void> {
        private CurrentWeatherDataDao currentWeatherDataDao;

        private UpdateCurrentWeatherDataAsyncTask(CurrentWeatherDataDao currentWeatherDataDao) {
            this.currentWeatherDataDao = currentWeatherDataDao;
        }

        @Override
        protected Void doInBackground(CurrentWeatherData... currentWeatherData) {
            currentWeatherDataDao.update(currentWeatherData[0]);
            return null;
        }
    }

    private static class DeleteCurrentWeatherDataAsyncTask extends AsyncTask<CurrentWeatherData, Void, Void> {
        private CurrentWeatherDataDao currentWeatherDataDao;

        private DeleteCurrentWeatherDataAsyncTask(CurrentWeatherDataDao currentWeatherDataDao) {
            this.currentWeatherDataDao = currentWeatherDataDao;
        }

        @Override
        protected Void doInBackground(CurrentWeatherData... currentWeatherData) {
            currentWeatherDataDao.delete(currentWeatherData[0]);
            return null;
        }
    }

    private static class DeleteAllCurrentWeatherDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private CurrentWeatherDataDao currentWeatherDataDao;

        private DeleteAllCurrentWeatherDataAsyncTask(CurrentWeatherDataDao currentWeatherDataDao) {
            this.currentWeatherDataDao = currentWeatherDataDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            currentWeatherDataDao.deleteAllCurrentWeatherData();
            return null;
        }
    }

    private static class InsertHourForecastWeatherDataAsyncTask extends AsyncTask<HourForecastWeatherData, Void, Void> {
        private HourForecastWeatherDataDao hourForecastWeatherDataDao;

        private InsertHourForecastWeatherDataAsyncTask(HourForecastWeatherDataDao hourForecastWeatherDataDao) {
            this.hourForecastWeatherDataDao = hourForecastWeatherDataDao;
        }

        @Override
        protected Void doInBackground(HourForecastWeatherData... hourForecastWeatherData) {
            hourForecastWeatherDataDao.insert(hourForecastWeatherData[0]);
            return null;
        }
    }

    private static class UpdateHourForecastWeatherDataAsyncTask extends AsyncTask<HourForecastWeatherData, Void, Void> {
        private HourForecastWeatherDataDao hourForecastWeatherDataDao;

        private UpdateHourForecastWeatherDataAsyncTask(HourForecastWeatherDataDao hourForecastWeatherDataDao) {
            this.hourForecastWeatherDataDao = hourForecastWeatherDataDao;
        }

        @Override
        protected Void doInBackground(HourForecastWeatherData... hourForecastWeatherData) {
            hourForecastWeatherDataDao.update(hourForecastWeatherData[0]);
            return null;
        }
    }

    private static class DeleteHourForecastWeatherDataAsyncTask extends AsyncTask<HourForecastWeatherData, Void, Void> {
        private HourForecastWeatherDataDao hourForecastWeatherDataDao;

        private DeleteHourForecastWeatherDataAsyncTask(HourForecastWeatherDataDao hourForecastWeatherDataDao) {
            this.hourForecastWeatherDataDao = hourForecastWeatherDataDao;
        }

        @Override
        protected Void doInBackground(HourForecastWeatherData... hourForecastWeatherData) {
            hourForecastWeatherDataDao.delete(hourForecastWeatherData[0]);
            return null;
        }
    }

    private static class DeleteAllHourForecastWeatherDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private HourForecastWeatherDataDao hourForecastWeatherDataDao;

        private DeleteAllHourForecastWeatherDataAsyncTask(HourForecastWeatherDataDao hourForecastWeatherDataDao) {
            this.hourForecastWeatherDataDao = hourForecastWeatherDataDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            hourForecastWeatherDataDao.deleteAllHourForecastWeatherData();
            return null;
        }
    }

    public LiveData<CurrentWeatherData> getCurrentWeatherDatabyCity(String cityName) {
        getCurrentForecast(cityName);
        return currentWeatherDataDao.getLiveCurrentWeatherDataByCity(cityName);
    }

    public void getCurrentForecast(String cityName) {
        //Log.v("getCurrentForecast", "Executed");
        new Thread() {
            @Override
            public void run() {
                super.run();
                currentWeatherDataDao.getCurrentWeatherDataByCity(cityName);
                try {
                    CurrentForecastApi response = apiClient.getCurrentForecast(cityName).execute().body();
                    if (response != null) {
                        CurrentWeatherData apiData = new CurrentWeatherData(cityName, "null", (int) response.getTemperature());
                        CurrentWeatherData databaseData = currentWeatherDataDao.getCurrentWeatherDataByCity(cityName);
                        if (databaseData != null) {
                            //Log.v("getCurrentForecast", cityName + "Database temp: " + databaseData.getTemperature());
                            //Log.v("getCurrentForecast", cityName + "API temp: " + apiData.getTemperature());
                            if (databaseData.getTemperature() != apiData.getTemperature()) {

                                //Log.v("getCurrentForecast", "WILL UPDATE !!!");
                                currentWeatherDataDao.UpdateCurrentWeatherDataByCity(apiData.getCityName(), apiData.getTemperature());
                            }
                        } else
                            currentWeatherDataDao.insert(apiData);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public LiveData<List<HourForecastWeatherData>> getWeekWeatherDatabyCity(String cityName) {
        getWeekForecast(cityName);
        return hourForecastWeatherDataDao.getLiveHourWeatherDataByCity(cityName);
    }

    public void getWeekForecast(String cityName) {

        String cityname = (cityName.split(","))[0];
        //Log.v("getWeekForecast", "Executed, " + cityname);
        new Thread() {
            @Override
            public void run() {
                super.run();
                hourForecastWeatherDataDao.getLiveHourWeatherDataByCity(cityName);
                try {

                    WeekWeatherForecastApi response = apiClient.getWeekForecast(cityname).execute().body();
                    if (response != null) {
                        List<WeekWeatherForecastApi.ForecastData> weekapirawdata = response.getForecastDataList();
                        //Log.v("getWeekForecast", "List Size: " + weekapirawdata.size());
                        ArrayList<HourForecastWeatherData> apidata = new ArrayList<>();
                        for (WeekWeatherForecastApi.ForecastData data : weekapirawdata) {
                            apidata.add(new HourForecastWeatherData(cityName, data.getDate() * 1000L, data.getWeatherData().getDescription(), Utils.getIcon(data.getWeatherData().getIcon()), data.getTemperature()));
                        }
                        ArrayList<HourForecastWeatherData> databaseData = (ArrayList) hourForecastWeatherDataDao.getHourWeatherDataByCity(cityName);
                        if (!databaseData.isEmpty()) {
                            //Log.v("getWeekForecast","DATABASE NOT EMPTY");
                            if (databaseData.get(0).getCity().equals(apidata.get(0).getCity())
                                    && databaseData.get(0).getDate() != apidata.get(0).getDate()) {
                               // Log.v("getWeekForecast","WILL DELETE AND INSERT");
                                hourForecastWeatherDataDao.deleteAllHourForecastWeatherDataByCity(cityName);
                                for (HourForecastWeatherData data : apidata) {
                                    hourForecastWeatherDataDao.insert(data);
                                }
                            }
                        } else {
                            //Log.v("getWeekForecast","WILL INSERT");
                            for (HourForecastWeatherData data : apidata) {
                                hourForecastWeatherDataDao.insert(data);
                            }
                        }

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
