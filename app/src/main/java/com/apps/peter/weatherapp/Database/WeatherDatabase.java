package com.apps.peter.weatherapp.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.apps.peter.weatherapp.Dao.CurrentWeatherDataDao;
import com.apps.peter.weatherapp.Dao.HourForecastWeatherDataDao;
import com.apps.peter.weatherapp.DataStructure.CurrentWeatherData;
import com.apps.peter.weatherapp.DataStructure.HourForecastWeatherData;

@Database(entities = {CurrentWeatherData.class, HourForecastWeatherData.class}, version = 1, exportSchema = false)
public abstract class WeatherDatabase extends RoomDatabase {
    private static WeatherDatabase instance;

    public abstract CurrentWeatherDataDao CurrentWeatherDataDao();

    public abstract HourForecastWeatherDataDao HourForecastWeatherDataDao();

    public static synchronized WeatherDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    WeatherDatabase.class, "weather_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private CurrentWeatherDataDao currentWeatherDataDao;

        private PopulateDbAsyncTask(WeatherDatabase db) {
            currentWeatherDataDao = db.CurrentWeatherDataDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            currentWeatherDataDao.insert(new CurrentWeatherData("Cairo", "test", 21));
            currentWeatherDataDao.insert(new CurrentWeatherData("London", "test", 22));
            currentWeatherDataDao.insert(new CurrentWeatherData("Barcelona", "test", 23));
            currentWeatherDataDao.insert(new CurrentWeatherData("Paris", "test", 24));
            currentWeatherDataDao.insert(new CurrentWeatherData("Munich", "test", 25));

            return null;
        }
    }
}
