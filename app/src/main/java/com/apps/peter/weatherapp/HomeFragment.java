package com.apps.peter.weatherapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HomeFragment extends Fragment implements View.OnClickListener {
    private TextView tv_first_city;
    private TextView tv_second_city;
    private TextView tv_third_city;
    private TextView tv_fourth_city;
    private TextView tv_fifth_city;
    private FragmentInteractionListener interactionListener;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_first_city = getActivity().findViewById(R.id.tv_first_city);
        tv_second_city = getActivity().findViewById(R.id.tv_second_city);
        tv_third_city = getActivity().findViewById(R.id.tv_third_city);
        tv_fourth_city = getActivity().findViewById(R.id.tv_fourth_city);
        tv_fifth_city = getActivity().findViewById(R.id.tv_fifth_city);
        tv_first_city.setOnClickListener(this);
        tv_second_city.setOnClickListener(this);
        tv_third_city.setOnClickListener(this);
        tv_fourth_city.setOnClickListener(this);
        tv_fifth_city.setOnClickListener(this);
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
        if (view.getId() == R.id.tv_first_city){
            this.interactionListener.gotoForecastFragment(1,"Cairo, EG");
        }else if (view.getId() == R.id.tv_second_city){
            this.interactionListener.gotoForecastFragment(1,"London, UK");
        }
        else if (view.getId() == R.id.tv_third_city){
            this.interactionListener.gotoForecastFragment(1,"Barcelona, SP");
        }
        else if (view.getId() == R.id.tv_fourth_city){
            this.interactionListener.gotoForecastFragment(1,"Paris, FR");
        }
        else if (view.getId() == R.id.tv_fifth_city){
            this.interactionListener.gotoForecastFragment(1,"Munich, GR");
        }
    }

    public void setInteractionListener(FragmentInteractionListener interactionListener) {
        this.interactionListener = interactionListener;
    }
}
