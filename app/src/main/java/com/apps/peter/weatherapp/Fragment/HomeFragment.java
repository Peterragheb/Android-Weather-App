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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apps.peter.weatherapp.DataStructure.CurrentWeatherData;
import com.apps.peter.weatherapp.Listener.FragmentInteractionListener;
import com.apps.peter.weatherapp.R;
import com.apps.peter.weatherapp.ViewModel.CurrentWeatherDataViewModel;


public class HomeFragment extends Fragment implements View.OnClickListener {
    private LinearLayout ll_firstCity, ll_secondCity, ll_thirdCity, ll_fourthCity, ll_fifthCity;
    private TextView tv_firstCityName, tv_secondCityName, tv_thirdCityName, tv_fourthCityName, tv_fifthCityName;
    private TextView tv_firstCityTemp, tv_secondCityTemp, tv_thirdCityTemp, tv_fourthCityTemp, tv_fifthCityTemp;
    private FragmentInteractionListener interactionListener;
    private CurrentWeatherDataViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUIComponents();
        initComponents();

    }

    private void initUIComponents() {
        ll_firstCity = getActivity().findViewById(R.id.ll_firstCity);
        ll_secondCity = getActivity().findViewById(R.id.ll_secondCity);
        ll_thirdCity = getActivity().findViewById(R.id.ll_thirdCity);
        ll_fourthCity = getActivity().findViewById(R.id.ll_fourthCity);
        ll_fifthCity = getActivity().findViewById(R.id.ll_fifthCity);
        tv_firstCityName = getActivity().findViewById(R.id.tv_firstCityName);
        tv_secondCityName = getActivity().findViewById(R.id.tv_secondCityName);
        tv_thirdCityName = getActivity().findViewById(R.id.tv_thirdCityName);
        tv_fourthCityName = getActivity().findViewById(R.id.tv_fourthCityName);
        tv_fifthCityName = getActivity().findViewById(R.id.tv_fifthCityName);
        tv_firstCityTemp = getActivity().findViewById(R.id.tv_firstCityTemp);
        tv_secondCityTemp = getActivity().findViewById(R.id.tv_secondCityTemp);
        tv_thirdCityTemp = getActivity().findViewById(R.id.tv_thirdCityTemp);
        tv_fourthCityTemp = getActivity().findViewById(R.id.tv_fourthCityTemp);
        tv_fifthCityTemp = getActivity().findViewById(R.id.tv_fifthCityTemp);
        ll_firstCity.setOnClickListener(this);
        ll_secondCity.setOnClickListener(this);
        ll_thirdCity.setOnClickListener(this);
        ll_fourthCity.setOnClickListener(this);
        ll_fifthCity.setOnClickListener(this);
    }

    private void initComponents() {
        viewModel = ViewModelProviders.of(this).get(CurrentWeatherDataViewModel.class);
        viewModel.getCairoCurrentWeatherData().observe(this, new Observer<CurrentWeatherData>() {
            @Override
            public void onChanged(@Nullable CurrentWeatherData currentWeatherData) {
                // Log.v("CurrentWeatherData", "CityName: " + currentWeatherData.getCityName() + " Temp: " + currentWeatherData.getTemperature());
                if (currentWeatherData != null)
                    tv_firstCityTemp.setText(currentWeatherData.getTemperature() + "");
            }
        });
        viewModel.getLondonCurrentWeatherData().observe(this, new Observer<CurrentWeatherData>() {
            @Override
            public void onChanged(@Nullable CurrentWeatherData currentWeatherData) {
                if (currentWeatherData != null)
                    tv_secondCityTemp.setText(currentWeatherData.getTemperature() + "");
            }
        });
        viewModel.getBarcelonaCurrentWeatherData().observe(this, new Observer<CurrentWeatherData>() {
            @Override
            public void onChanged(@Nullable CurrentWeatherData currentWeatherData) {
                if (currentWeatherData != null)
                    tv_thirdCityTemp.setText(currentWeatherData.getTemperature() + "");
            }
        });
        viewModel.getParisCurrentWeatherData().observe(this, new Observer<CurrentWeatherData>() {
            @Override
            public void onChanged(@Nullable CurrentWeatherData currentWeatherData) {
                if (currentWeatherData != null)
                    tv_fourthCityTemp.setText(currentWeatherData.getTemperature() + "");
            }
        });
        viewModel.getMunichCurrentWeatherData().observe(this, new Observer<CurrentWeatherData>() {
            @Override
            public void onChanged(@Nullable CurrentWeatherData currentWeatherData) {
                if (currentWeatherData != null)
                    tv_fifthCityTemp.setText(currentWeatherData.getTemperature() + "");
            }
        });
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
        if (view.getId() == R.id.ll_firstCity) {
            this.interactionListener.gotoForecastFragment(1, "Cairo, EG");
        } else if (view.getId() == R.id.ll_secondCity) {
            this.interactionListener.gotoForecastFragment(1, "London, UK");
        } else if (view.getId() == R.id.ll_thirdCity) {
            this.interactionListener.gotoForecastFragment(1, "Barcelona, SP");
        } else if (view.getId() == R.id.ll_fourthCity) {
            this.interactionListener.gotoForecastFragment(1, "Paris, FR");
        } else if (view.getId() == R.id.ll_fifthCity) {
            this.interactionListener.gotoForecastFragment(1, "Munich, GR");
        }
    }

    public void setInteractionListener(FragmentInteractionListener interactionListener) {
        this.interactionListener = interactionListener;
    }
}
