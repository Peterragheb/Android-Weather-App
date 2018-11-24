package com.apps.peter.weatherapp.Listener;

public interface FragmentInteractionListener {
    void gotoForecastFragment(int transactionState, String cityName);
    void gotoHomeFragment();
    void gotoAboutFragment(int transactionState);
    void gotoDailyForecastFragment(int transactionState,String cityName, int position);
}
