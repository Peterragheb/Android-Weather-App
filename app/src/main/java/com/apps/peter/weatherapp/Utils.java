package com.apps.peter.weatherapp;

public class Utils {
    public static int getIcon(String iconID) {
        switch (iconID) {
            case "01d": {
                return R.drawable.ic_sun;
            }

            case "01n": {
                return R.drawable.ic_moon_stars;
            }

            case "02d": {
                return R.drawable.ic_cloud_sun;
            }

            case "02n": {
                return R.drawable.ic_cloud_moon;
            }

            case "03d": {
                return R.drawable.ic_cloud;
            }

            case "03n": {
                return R.drawable.ic_cloud;
            }

            case "04d": {
                return R.drawable.ic_clouds;
            }

            case "04n": {
                return R.drawable.ic_clouds;
            }

            case "09d": {
                return R.drawable.ic_cloud_rain;
            }

            case "09n": {
                return R.drawable.ic_cloud_rain;
            }

            case "10d": {
                return R.drawable.ic_cloud_rain_sun;
            }

            case "10n": {
                return R.drawable.ic_cloud_rain_moon;
            }

            case "11d": {
                return R.drawable.ic_cloud_lightning;
            }

            case "11n": {
                return R.drawable.ic_cloud_lightning;
            }

            case "13d": {
                return R.drawable.ic_cloud_snow;
            }

            case "13n": {
                return R.drawable.ic_cloud_snow;
            }

            case "50d": {
                return R.drawable.ic_fog;
            }

            case "50n": {
                return R.drawable.ic_fog;
            }

            default:
                return R.drawable.ic_sun;
        }
    }
}
