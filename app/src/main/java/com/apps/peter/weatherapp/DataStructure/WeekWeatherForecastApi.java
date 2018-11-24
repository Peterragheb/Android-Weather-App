package com.apps.peter.weatherapp.DataStructure;

import android.text.format.DateFormat;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class WeekWeatherForecastApi {
    public List<ForecastData> getForecastDataList() {
        return forecastDataList;
    }

    @SerializedName("list")
    List<ForecastData> forecastDataList;

    public class ForecastData {
        @SerializedName("dt")
        private long date;
        @SerializedName("main")
        private temperatureObject temperatureObject;
        @SerializedName("weather")
        private List<weatherData> weatherData;

        public int getTemperature() {
            return (int) temperatureObject.getTemperature();
        }


        public weatherData getWeatherData() {
            return weatherData.get(0);
        }

        public String getDateString() {
            Calendar cal = Calendar.getInstance(Locale.getDefault());
            cal.setTimeInMillis(date * 1000L);
            String dateValue = DateFormat.format("dd/MM/yyyy hh:mm:ss", cal).toString();
            return dateValue;
        }

        public long getDate() {
            return date;
        }

        public class temperatureObject {
            @SerializedName("temp")
            float temprature;

            public float getTemperature() {
                return temprature;
            }
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

            public String getIcon() {
                return icon;
            }
        }
    }

}
