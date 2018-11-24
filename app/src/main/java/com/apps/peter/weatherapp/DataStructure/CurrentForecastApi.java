package com.apps.peter.weatherapp.DataStructure;

import android.text.format.DateFormat;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CurrentForecastApi {
    @SerializedName("weather")
    private List<weatherData> weatherData;

    @SerializedName("main")
    private tempratureObject tempratureObject;

    @SerializedName("dt")
    private long date;


    public weatherData getWeatherData() {
        return weatherData.get(0);
    }

    public int getTemperature() {
        return (int) tempratureObject.getTemperature();
    }

    public String getDate() {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTimeInMillis(date * 1000L);
        String dateValue = DateFormat.format("dd/MM/yyyy hh:mm:ss", cal).toString();
        return dateValue;
    }


    public class weatherData {
        int id;
        @SerializedName("main")
        String description;
        @SerializedName("description")
        String fullDescription;
        String icon;

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }
    }

    public class tempratureObject {
        @SerializedName("temp")
        float temprature;
        float pressure;
        float humidity;
        float temp_min;
        float temp_max;

        public float getTemperature() {
            return temprature;
        }
    }

}
