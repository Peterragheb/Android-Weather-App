package com.apps.peter.weatherapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class ForecastFragment extends Fragment implements View.OnClickListener {
    private ListView lv_forecast_list;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_day_forecast,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv_forecast_list = getActivity().findViewById(R.id.lv_forecast_list);
        ArrayList<DayWeather> days = new ArrayList<>();
        days.add(new DayWeather("Today","Sunny",R.drawable.ic_sun_rise,30,21));
        days.add(new DayWeather("Monday","Sunny",R.drawable.ic_sun_rise,32,19));
        days.add(new DayWeather("Tuesday","Foggy",R.drawable.ic_sun_rise,41,11));
        days.add(new DayWeather("Wednesday","Rainy",R.drawable.ic_sun_rise,20,1));
        days.add(new DayWeather("Thursday","Sunny",R.drawable.ic_sun_rise,50,2));
        ListViewAdapter adapter = new ListViewAdapter(getContext(),R.layout.list_first_item,days);
        lv_forecast_list.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
    }


}
