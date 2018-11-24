package com.apps.peter.weatherapp.Fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.apps.peter.weatherapp.Adapter.HourListViewAdapter;
import com.apps.peter.weatherapp.DataStructure.HourForecastWeatherData;
import com.apps.peter.weatherapp.R;
import com.apps.peter.weatherapp.ViewModel.HourForecastWeatherDataViewModel;

import java.util.ArrayList;
import java.util.List;

public class DayWeatherForecastFragment extends Fragment {
    private ListView lv_forecast_list;
    private HourForecastWeatherDataViewModel viewModel;
    private String cityName;
    private int position;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city_forecast, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUIComponents(savedInstanceState);
    }

    private void initUIComponents(@Nullable Bundle savedInstanceState) {
        lv_forecast_list = getActivity().findViewById(R.id.lv_forecast_list);
        ArrayList<HourForecastWeatherData> days = new ArrayList<>();
        viewModel = ViewModelProviders.of(this).get(HourForecastWeatherDataViewModel.class);
        if (savedInstanceState == null)
            viewModel.setcityName(cityName);
        HourListViewAdapter adapter = new HourListViewAdapter(getContext(), R.layout.day_list_first_item, days);
        viewModel.getAllHourWeatherData().observe(this, new Observer<List<HourForecastWeatherData>>() {
            @Override
            public void onChanged(@Nullable List<HourForecastWeatherData> hourForecastWeatherData) {
                //Log.v("onChanged", "List Size: " + hourForecastWeatherData.size());
                adapter.setListData((ArrayList<HourForecastWeatherData>) hourForecastWeatherData, position);
            }
        });
        lv_forecast_list.setAdapter(adapter);
    }

    private void initComponents(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            cityName = savedInstanceState.getString("cityName", "");
            position = savedInstanceState.getInt("position", 0);
        }
    }

    public void setProperties(String cityName, int position) {
        this.cityName = cityName;
        this.position = position;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("cityName", cityName);
        outState.putInt("position", position);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
