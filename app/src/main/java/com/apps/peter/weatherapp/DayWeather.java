package com.apps.peter.weatherapp;

public class DayWeather {
    private String date;
    private String description;
    private int icon;
    private int tempHigh;
    private int tempLow;

    public DayWeather(String date, String description, int icon, int tempHigh, int tempLow) {
        this.date = date;
        this.description = description;
        this.icon = icon;
        this.tempHigh = tempHigh;
        this.tempLow = tempLow;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getTempHigh() {
        return tempHigh;
    }

    public void setTempHigh(int tempHigh) {
        this.tempHigh = tempHigh;
    }

    public int getTempLow() {
        return tempLow;
    }

    public void setTempLow(int tempLow) {
        this.tempLow = tempLow;
    }
}
