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
import android.widget.AdapterView;
import android.widget.ListView;

import com.apps.peter.weatherapp.Adapter.DayListViewAdapter;
import com.apps.peter.weatherapp.DataStructure.HourForecastWeatherData;
import com.apps.peter.weatherapp.Listener.FragmentInteractionListener;
import com.apps.peter.weatherapp.R;
import com.apps.peter.weatherapp.ViewModel.HourForecastWeatherDataViewModel;

import java.util.ArrayList;
import java.util.List;


public class ForecastFragment extends Fragment implements View.OnClickListener {
    private ListView lv_forecast_list;
    private FragmentInteractionListener interactionListener;
    private HourForecastWeatherDataViewModel viewModel;
    private String cityName;

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
        viewModel = ViewModelProviders.of(this).get(HourForecastWeatherDataViewModel.class);
        if (savedInstanceState == null)
            viewModel.setcityName(cityName);
        DayListViewAdapter adapter = new DayListViewAdapter(getContext(), R.layout.day_list_item, new ArrayList<HourForecastWeatherData>());
        viewModel.getAllHourWeatherData().observe(this, new Observer<List<HourForecastWeatherData>>() {
            @Override
            public void onChanged(@Nullable List<HourForecastWeatherData> hourForecastWeatherData) {
                //Log.v("onChanged", "List Size: " + hourForecastWeatherData.size());
                adapter.setListData((ArrayList<HourForecastWeatherData>) hourForecastWeatherData);
            }
        });
        lv_forecast_list.setAdapter(adapter);
        lv_forecast_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                interactionListener.gotoDailyForecastFragment(4, cityName, i);
            }
        });
    }

    private void initComponents(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null)
            cityName = savedInstanceState.getString("cityName", "");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("cityName", cityName);
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

    public void setcityName(String cityName) {
        this.cityName = cityName;
    }

    public void setInteractionListener(FragmentInteractionListener interactionListener) {
        this.interactionListener = interactionListener;
    }
}
