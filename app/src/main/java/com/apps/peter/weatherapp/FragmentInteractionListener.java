package com.apps.peter.weatherapp;

public interface FragmentInteractionListener {
    void gotoForecastFragment(int transactionState, String cityName);
    void gotoHomeFragment();
    void gotoAboutFragment(int transactionState);
}
